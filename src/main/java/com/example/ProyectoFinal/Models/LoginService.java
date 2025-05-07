package com.example.ProyectoFinal.Models;

import com.example.ProyectoFinal.Repository.TokenLoginRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TokenLoginRepository tokenRepo;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepo.findByCorreoAndDocumento(request.getCorreo(), request.getDocumento())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String nuevoToken = UUID.randomUUID().toString();
        LocalDateTime expiracion = LocalDateTime.now().plusDays(1);

        Optional<TokenLogin> existente = tokenRepo.findByUsuario(usuario);

        TokenLogin tokenLogin;
        if (existente.isPresent()) {
            tokenLogin = existente.get();
            tokenLogin.setToken(nuevoToken);
            tokenLogin.setExpiracion(expiracion);
        } else {
            tokenLogin = new TokenLogin();
            tokenLogin.setId(UUID.randomUUID());
            tokenLogin.setUsuario(usuario);
            tokenLogin.setToken(nuevoToken);
            tokenLogin.setExpiracion(expiracion);
        }

        tokenRepo.save(tokenLogin);

        return new LoginResponse(usuario.getId(), usuario.getRol(), tokenLogin.getToken(), tokenLogin.getExpiracion());
    }
}
