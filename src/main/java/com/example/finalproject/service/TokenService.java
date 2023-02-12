package com.example.finalproject.service;

import com.example.finalproject.domain.User;
import com.example.finalproject.exception.UserDoesntExist;
import com.example.finalproject.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    private final static int tokenExpirationTime = 3600000;

    private static final String secret = "d3ZVkYbtq573uigugE46NOfiv_mx5eqKwPwjNcUta-Q2HvzD";

    public final static Key signedKey = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());

    public String generateToken(String username, String password) throws UserDoesntExist {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UserDoesntExist();
        }

        boolean pass = bCryptPasswordEncoder.matches(password, user.getPassword());
        if(!user.getUsername().equals(username) || !pass) {
            throw new UserDoesntExist();
        }

        return Jwts.builder()
                .setHeaderParam("HS512", "JWT")
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .signWith(signedKey)
                .compact();
    }


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(signedKey).build().parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()));
    }
}
