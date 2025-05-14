package pheng.com.springfirstclass.base;

import lombok.Builder;

import java.util.Date;

@Builder
public record BaseResponse<T>(
        String code,
        String message,
        Date timestamp,
        T data
) {
}
