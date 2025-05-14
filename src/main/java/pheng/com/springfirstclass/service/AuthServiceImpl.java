package pheng.com.springfirstclass.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.model.dto.JwtAsset;
import pheng.com.springfirstclass.model.dto.LoginDto;
import pheng.com.springfirstclass.repo.UserRepository;
import pheng.com.springfirstclass.security.JWTUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;
    public JwtAsset login(LoginDto user) {
        User user1  = userRepository.findUserByEmail(user.email());
        if(passwordEncoder.encode(user.password()).equals(user1.getPassword())){
            throw new BadCredentialsException("Invalid email or password");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user1.getUuid(), user.password())
        );
        String accessToken = jwtUtil.generateAccessToken(user1.getUuid(), authentication.getAuthorities());
        String refreshToken = jwtUtil.generateRefreshToken(user1.getUuid());
        return JwtAsset.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtAsset refreshToken(String refreshToken) {
        assert refreshToken != null;
        if(jwtUtil.isAccessTokenType(refreshToken)){
            throw new BadCredentialsException("This is not a valid refresh token");
        }
        boolean isTokenValid = jwtUtil.isTokenValid(refreshToken);// verify is valid token, it will throw an exception is not valid :)
        User user = userRepository.findUserByUuid(jwtUtil.extractSubject(refreshToken));
        String accessToken = jwtUtil.generateAccessToken(user.getUuid(), user.getAuthorities());
        String newRefreshToken = jwtUtil.generateRefreshToken(user.getUuid());
        return JwtAsset.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
