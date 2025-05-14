package pheng.com.springfirstclass.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import pheng.com.springfirstclass.base.BaseException;

import java.time.Instant;
import java.util.Date;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<BaseException> handleUserException(UserAlreadyExistException e) {
        return new ResponseEntity<>(BaseException
                .builder()
                .code(HttpStatus.CONFLICT.toString())
                .message(e.getMessage())
                .timeStamp(Date.from(Instant.now()))
                .build(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<BaseException> handleUserNotFound(UserNotFoundException exception){
        return new ResponseEntity<>(BaseException
                .builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .message(exception.getMessage())
                .timeStamp(Date.from(Instant.now()))
                .build(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ResponseEntity<BaseException> handleBadCredentialsException(BadCredentialsException exception){
        return new ResponseEntity<>(BaseException
                .builder()
                .code(HttpStatus.FORBIDDEN.toString())
                .message(exception.getMessage())
                .timeStamp(Date.from(Instant.now()))
                .build(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(JwtException.class)
    @ResponseBody
    public ResponseEntity<BaseException> handleJwtException(JwtException exception){
      return new ResponseEntity<>(BaseException
                .builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(exception.getMessage())
                .timeStamp(Date.from(Instant.now()))
                .build(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseBody
    public ResponseEntity<BaseException> handleIllegalAccessException(IllegalAccessException exception){
        return new ResponseEntity<>(BaseException
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .message(exception.getMessage())
                .timeStamp(Date.from(Instant.now()))
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
