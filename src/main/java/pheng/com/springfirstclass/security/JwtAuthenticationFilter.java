package pheng.com.springfirstclass.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pheng.com.springfirstclass.base.BaseException;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.repo.UserRepository;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{

            String accessToken = request.getHeader("Authorization");
            if(accessToken != null && accessToken.startsWith("Bearer ")) {
                accessToken = accessToken.substring(6);
                // verify token between the private encrypted key and public key
                boolean isRight = jwtUtil.isTokenValid(accessToken);
                if(isRight){
                    //
                    String uuid = jwtUtil.extractSubject(accessToken);
                    // get authorize
                    List<String> roles = jwtUtil.extractRoles(accessToken);
                    List<GrantedAuthority> authorities = roles.stream()
                            .map(r->new SimpleGrantedAuthority("ROLE_" + r))
                            .collect(Collectors.toList());
                    UsernamePasswordAuthenticationToken
                            // validate where the user has authority
                            authToken = new UsernamePasswordAuthenticationToken(uuid, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        }catch (JwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=utf-8");
            String json = new ObjectMapper().writeValueAsString(
                    BaseException.builder()
                            .code(HttpStatus.UNAUTHORIZED.toString())
                            .message(e.getMessage())
                            .timeStamp(Date.from(Instant.now()))
                            .build()
            );
            response.getWriter().write(json);
        }
    }
}
