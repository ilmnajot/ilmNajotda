package uz.ilmnajot.samps.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final String secretKey = "mySecretKeyIsThis";
    private final long expireTime = 365 * 24 * 3600 * 1000L;

    public String generateToken(String username){
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
    public String getTokenFromUsername(String username) {
       try {
           return Jwts
                   .parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(username)
                   .getBody()
                   .getSubject();
       }catch (Exception e){
           return null;
       }
    }
}
