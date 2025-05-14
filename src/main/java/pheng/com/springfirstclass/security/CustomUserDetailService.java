package pheng.com.springfirstclass.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pheng.com.springfirstclass.model.User;
import pheng.com.springfirstclass.repo.UserRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        User user = userRepository.findUserByUuid(uuid);
        if(user == null){
            throw new UsernameNotFoundException("User has not been registered yet :(");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(r-> new SimpleGrantedAuthority(r.getName()))
                        .toList()
        );
    }
}
