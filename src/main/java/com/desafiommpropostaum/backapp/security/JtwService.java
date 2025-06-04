package com.desafiommpropostaum.backapp.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.desafiommpropostaum.backapp.models.User;
import com.desafiommpropostaum.backapp.repositories.UserRepository;
import com.desafiommpropostaum.backapp.services.UserService;

@Service
public class JtwService {
    private final JwtEncoder encoder;
    private final UserService userService;
    private final UserRepository userRepository;

    public JtwService(JwtEncoder encoder, UserService userService, UserRepository userRepository) {
        this.encoder = encoder;
        this.userService = userService;
        this.userRepository = userRepository;
    }



    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;

        String scopes = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

        String username = authentication.getName();
        String userId = null;

        User user = userRepository.findByUsername(username).get();

        if (user != null) {
            userId = user.getUser_id().toString();
        }

        var claims = JwtClaimsSet.builder()
        .issuer("spring-security-jwt")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", scopes)
        .claim("user_id", userId)
        .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
