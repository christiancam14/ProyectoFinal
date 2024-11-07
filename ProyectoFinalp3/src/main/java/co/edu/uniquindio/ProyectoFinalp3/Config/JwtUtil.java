package co.edu.uniquindio.ProyectoFinalp3.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private Algorithm algorithm;

    // Constructor que inicializa el Algorithm basado en el SECRET_KEY
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
        this.algorithm = Algorithm.HMAC256(secretKey); // Inicializa el algoritmo con el secretKey dinámico
    }

    public String create(String correoElectronico) {
        return JWT.create()
                .withSubject(correoElectronico)
                .withIssuer("Market-Place")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(algorithm);
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(algorithm)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getEmail(String jwt) {
        return JWT.require(algorithm)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
