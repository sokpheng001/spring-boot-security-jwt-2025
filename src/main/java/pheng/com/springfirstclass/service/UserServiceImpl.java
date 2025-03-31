package pheng.com.springfirstclass.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pheng.com.springfirstclass.mapper.UserMapper;
import pheng.com.springfirstclass.model.Role;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.model.dto.CreateUserDto;
import pheng.com.springfirstclass.model.dto.UserResponseDto;
import pheng.com.springfirstclass.repo.RoleRepository;
import pheng.com.springfirstclass.repo.UserRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final RoleRepository roleRepository;
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> result = new ArrayList<>();
        userRepository.findAll().forEach(e->{
            result.add(userMapper.fromUserToUserResponseDto(e));
        });
        return result;
    }
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.fromCreateUserDtotoUser(createUserDto);
        user.setUuid(UUID.randomUUID().toString());
        user.setCreatedDate(Date.valueOf(LocalDate.now()));
        // set role
        Role role = roleRepository.findRoleByUuid("b17ad81d-af85-4b13-8796-918584059d7b");
        //
        user.setRoles(new HashSet<>(Set.of(role)));
        // save to database
        userRepository.save(user);
        //
        return userMapper.fromUserToUserResponseDto(user);
    }
}
