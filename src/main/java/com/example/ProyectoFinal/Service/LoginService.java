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
    private TokenLoginService tokenLoginService;

    @Autowired
    private TokenLoginRepository tokenRepo;

    public Optional<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuario = usuarioRepo.findByCorreoAndDocumento(request.getCorreo(), request.getDocumento());
        if (usuario.isPresent()) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiracion = LocalDateTime.now().plusHours(2);

            TokenLogin tokenLogin = new TokenLogin();
            tokenLogin.setUsuario(usuario.get());
            tokenLoginService.getByToken(token);
            tokenLogin.setExpiracion(expiracion);
            tokenLoginService.save(tokenLogin);

            return Optional.of(new LoginResponse(
                    usuario.get().getId(),
                    usuario.get().getRol(),
                    token,
                    expiracion
            ));
        }
        return Optional.empty();
    }
}