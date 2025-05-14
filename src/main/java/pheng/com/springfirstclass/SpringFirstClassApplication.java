package pheng.com.springfirstclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pheng.com.springfirstclass.security.JWTUtil;
import pheng.com.springfirstclass.security.KeyUtil;

@SpringBootApplication
public class SpringFirstClassApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringFirstClassApplication.class, args);

    }
}
