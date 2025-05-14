package pheng.com.springfirstclass.model.dto;

import lombok.Builder;

@Builder
public record UpdateUserDto(
        String name,
        String email,
        String profile
) {
}
