package pheng.com.springfirstclass.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pheng.com.springfirstclass.SpringFirstClassApplication;
import pheng.com.springfirstclass.exception.UserAlreadyExistException;
import pheng.com.springfirstclass.exception.UserNotFoundException;
import pheng.com.springfirstclass.mapper.UserMapper;
import pheng.com.springfirstclass.model.Role;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.model.dto.CreateUserDto;
import pheng.com.springfirstclass.model.dto.UpdateUserDto;
import pheng.com.springfirstclass.model.dto.UserResponseDto;
import pheng.com.springfirstclass.repo.RoleRepository;
import pheng.com.springfirstclass.repo.UserRepository;
import pheng.com.springfirstclass.repo.UserRepositoryPaginate;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final RoleRepository roleRepository;
    private final UserRepositoryPaginate userRepositoryPaginate;
    private final PasswordEncoder passwordEncoder;
    public Page<UserResponseDto> getAllUsers(int page, int size) {
        //
//        return null;
        // sort for new user must be displayed first
        return userMapper.fromUserToUserResponseDtoPage(userRepositoryPaginate
                .findAll(PageRequest.of(page, size, Sort.Direction.DESC,"id")));
    }
    public UserResponseDto createUser(CreateUserDto createUserDto) throws UserAlreadyExistException {
        boolean isExisted = userRepository.existsUserByEmail(createUserDto.email());
        log.warn("isExisted: {}", isExisted);
        if(isExisted) {
            throw new UserAlreadyExistException("User with this email already existed.");
        }
        User user = userMapper.fromCreateUserDtotoUser(createUserDto);
        user.setUuid(UUID.randomUUID().toString());
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // set role
        Role role = roleRepository.findRoleByName("ROLE_USER");
        //
        user.setRoles(new HashSet<>(Set.of(role)));
        // save to database
        userRepository.save(user);
        //
        return userMapper.fromUserToUserResponseDto(user);
    }
    @Transactional
    public UserResponseDto updateUser(String uuid, UpdateUserDto updateUserDto) throws UserNotFoundException {
        User user = userRepository.findUserByUuid(uuid);
        if(user == null) {
            throw new UserNotFoundException("User not found for update.");
        }

        if(updateUserDto.name() != null && !updateUserDto.name().isEmpty()) {
            user.setName(updateUserDto.name());
        }

        if(updateUserDto.email() != null && !updateUserDto.email().isEmpty()) {
            user.setEmail(updateUserDto.email());
        }

        if(updateUserDto.profile() != null && !updateUserDto.profile().isEmpty()) {
            user.setProfile(updateUserDto.profile());
        }

        userRepository.save(user);
        return userMapper.fromUserToUserResponseDto(user);
    }
    public String deleteUser(String uuid){
        User isExisted = userRepository.findUserByUuid(uuid);
        if(isExisted!=null) {
            userRepository.delete(isExisted);
            return uuid;
        }
        throw new UserNotFoundException("User with this uuid does not exist.");
    }
}
