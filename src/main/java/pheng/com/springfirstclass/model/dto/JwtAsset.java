package pheng.com.springfirstclass.model.dto;

import lombok.Builder;

@Builder
public record JwtAsset(
        String accessToken,
        String refreshToken
) {
}
