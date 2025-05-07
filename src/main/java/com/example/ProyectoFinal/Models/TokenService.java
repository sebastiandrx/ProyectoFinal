package com.example.ProyectoFinal.Models;

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
        // Quitar prefijo Bearer si existe
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Buscar el token y verificar si aún no ha expirado
        Optional<TokenLogin> encontrado = tokenRepo.findByToken(token);
        return encontrado.isPresent() && encontrado.get().getExpiracion().isAfter(LocalDateTime.now());
    }
}
