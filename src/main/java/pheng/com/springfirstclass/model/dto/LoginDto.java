package pheng.com.springfirstclass.model.dto;

import lombok.Builder;

@Builder
public record LoginDto(
        String email,
        String password
) {
}
