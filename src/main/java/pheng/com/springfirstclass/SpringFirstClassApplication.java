package pheng.com.springfirstclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pheng.com.springfirstclass.security.JWTUtil;

@SpringBootApplication
public class SpringFirstClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFirstClassApplication.class, args);
        try{
            System.out.println("Private key: " + new JWTUtil().getPrivateKey());
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}
