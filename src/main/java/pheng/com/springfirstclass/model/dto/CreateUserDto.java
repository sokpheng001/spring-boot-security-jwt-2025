package pheng.com.springfirstclass.model.dto;

public record CreateUserDto(
        String name,
        String email,
        String password
) {
}
