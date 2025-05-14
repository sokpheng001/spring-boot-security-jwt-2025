package pheng.com.springfirstclass.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.model.dto.CreateUserDto;
import pheng.com.springfirstclass.model.dto.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User fromCreateUserDtotoUser(CreateUserDto createUserDto);
    UserResponseDto fromUserToUserResponseDto(User user);
    default Page<UserResponseDto> fromUserToUserResponseDtoPage(Page<User> users){
        List<UserResponseDto> content = users.getContent()
                .stream()
                .map(this::fromUserToUserResponseDto)
                .collect(Collectors.toList());
        return new PageImpl<>(content, users.getPageable(), users.getTotalElements());
    }
}
