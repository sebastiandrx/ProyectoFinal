package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.TokenLogin;
import com.example.ProyectoFinal.Repository.TokenLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenLoginRepository tokenRepo;

    public boolean tokenValido(String token) {
        if (token == null) return false;

        // Quitar "Bearer " si está presente
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Optional<TokenLogin> encontrado = tokenRepo.findByToken(token);
        return encontrado.isPresent() &&
                encontrado.get().getExpiracion() != null &&
                encontrado.get().getExpiracion().isAfter(LocalDateTime.now());
    }

    public void logout(String token) {
        if (token == null) return;

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        tokenRepo.findByToken(token).ifPresent(tokenRepo::delete);
    }
}
