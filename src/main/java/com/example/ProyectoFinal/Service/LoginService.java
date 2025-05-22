package com.example.ProyectoFinal.Service;
import com.example.ProyectoFinal.Models.LoginRequest;
import com.example.ProyectoFinal.Models.LoginResponse;
import com.example.ProyectoFinal.Models.TokenLogin;
import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.TokenLoginRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private TokenLoginService tokenLoginService;
    @Autowired
    private TokenLoginRepository tokenRepo;

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepo.findByCorreoAndDocumento(request.getCorreo(), request.getDocumento())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 🔥 Eliminar cualquier token viejo antes de intentar guardar uno nuevo
        tokenRepo.findByUsuario(usuario).ifPresent(anterior -> {
            tokenRepo.deleteById(anterior.getId());
        });

        // 🔐 Crear un nuevo token completamente limpio
        TokenLogin nuevo = new TokenLogin();
        nuevo.setUsuario(usuario);
        nuevo.setToken(UUID.randomUUID().toString());
        nuevo.setExpiracion(LocalDateTime.now().plusDays(1));

        // Guardar usando el servicio
        tokenLoginService.save(nuevo);

        return new LoginResponse(
                usuario.getId(),
                usuario.getRol(),
                nuevo.getToken(),
                nuevo.getExpiracion()
        );
    }

}