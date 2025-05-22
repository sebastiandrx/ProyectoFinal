package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.LoginRequest;
import com.example.ProyectoFinal.Models.LoginResponse;
import com.example.ProyectoFinal.Models.TokenLogin;
import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.TokenLoginRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private TokenLoginRepository tokenRepo;

    public Optional<LoginResponse> login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByCorreoAndDocumento(
                request.getCorreo(), request.getDocumento());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            String token = UUID.randomUUID().toString();
            LocalDateTime expiracion = LocalDateTime.now().plusHours(2);

            // ⚠️ IMPORTANTE: eliminar token anterior si existe
            tokenRepo.findByUsuario(usuario).ifPresent(tokenRepo::delete);

            TokenLogin nuevoToken = new TokenLogin();
            nuevoToken.setUsuario(usuario);
            nuevoToken.setToken(token);
            nuevoToken.setExpiracion(expiracion);
            tokenRepo.save(nuevoToken);

            return Optional.of(new LoginResponse(
                    usuario.getId(),
                    usuario.getRol(),
                    token,
                    expiracion
            ));
        }

        return Optional.empty();
    }
}
