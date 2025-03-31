package pheng.com.springfirstclass.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.model.dto.CreateUserDto;
import pheng.com.springfirstclass.model.dto.UserResponseDto;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User fromCreateUserDtotoUser(CreateUserDto createUserDto);
    UserResponseDto fromUserToUserResponseDto(User user);
}
