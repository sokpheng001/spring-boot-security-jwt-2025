package pheng.com.springfirstclass.base;

import lombok.Builder;

import java.util.Date;

@Builder
public record BaseException(
        String code,
        String message,
        Date timeStamp
) {
}
