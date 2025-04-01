package pheng.com.springfirstclass.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
public class JWTUtil {
    private static final Logger log = LoggerFactory.getLogger(JWTUtil.class);
    @Value("${jwt.private-key:NOT_FOUND}")
    private String privateKeyPath;
    @Value("${jwt.public-key}")
    private String publicKeyPath;
    @Value("${jwt.access-token.expire}")
    private long accessTokenExpiration;
    @Value("${jwt.refresh-token.expire}")
    private long refreshTokenExpiration;
    // Load private
    public PrivateKey getPrivateKey() throws Exception {
        System.out.println("privateKeyPath: " + privateKeyPath);
        byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));

        String privateKeyPEM = new String(keyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decodedKey = Base64.getDecoder().decode(privateKeyPEM);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
    }

}
