package pheng.com.springfirstclass.model.dto;

import pheng.com.springfirstclass.model.Role;

import java.sql.Date;
import java.util.Set;

public record UserResponseDto(
        String uuid,
        String name,
        String email,
        Date createdDate,
        String profile,
        Set<Role> roles
) {
}
