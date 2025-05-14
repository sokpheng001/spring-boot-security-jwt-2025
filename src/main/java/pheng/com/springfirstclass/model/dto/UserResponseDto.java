package pheng.com.springfirstclass.model.dto;

import java.sql.Date;
import java.util.Set;

public record UserResponseDto(
        String uuid,
        String name,
        String email,
        Date createdDate,
        String profile,
        Set<RoleDto> roles
) {
}
