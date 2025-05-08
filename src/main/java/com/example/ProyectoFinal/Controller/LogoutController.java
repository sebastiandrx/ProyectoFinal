package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.TokenService;
import com.example.ProyectoFinal.Repository.TokenLoginRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class LogoutController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TokenLoginRepository tokenRepo;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token inválido o ausente");
        }

        tokenService.logout(authHeader);
        return ResponseEntity.ok("Sesión cerrada exitosamente");
    }
}
