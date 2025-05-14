package pheng.com.springfirstclass.controller;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pheng.com.springfirstclass.base.BaseResponse;
import pheng.com.springfirstclass.model.dto.LoginDto;
import pheng.com.springfirstclass.model.dto.RefreshToken;
import pheng.com.springfirstclass.repo.UserRepository;
import pheng.com.springfirstclass.security.JWTUtil;
import pheng.com.springfirstclass.service.AuthServiceImpl;

import java.time.Instant;

import java.util.Date;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authServiceImpl;
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody LoginDto user){
        try{
            return new BaseResponse<>(HttpStatus.OK.toString(),
                        "Login Successfully",
                        Date.from(Instant.now()),
                        authServiceImpl.login(user));
        }catch (JwtException exception){
            throw new BadCredentialsException(exception.getMessage());
        }
    }

    @PostMapping("/refresh")
    public BaseResponse<Object> refreshToken(@RequestBody RefreshToken refreshToken ){
        try{
            return new BaseResponse<>(HttpStatus.OK.toString(),
                    "Get new JWT token Successfully",
                    Date.from(Instant.now()),
                    authServiceImpl.refreshToken(refreshToken.refreshToken()));
        }catch (Exception exception){
            throw new JwtException(exception.getMessage());
        }
    }
}


