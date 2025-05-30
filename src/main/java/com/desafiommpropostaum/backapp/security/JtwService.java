package com.desafiommpropostaum.backapp.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JtwService {
    private final JwtEncoder encoder;

    public JtwService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;

        String scopes = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));

        System.out.println("DEBUG: Claims scopes no JWT: " + scopes);

        var claims = JwtClaimsSet.builder()
        .issuer("spring-security-jwt")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", scopes)
        .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
