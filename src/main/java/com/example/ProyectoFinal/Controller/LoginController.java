package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.LoginRequest;
import com.example.ProyectoFinal.Models.LoginResponse;
import com.example.ProyectoFinal.Models.TokenLogin;
import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import com.example.ProyectoFinal.Service.LoginService;
import com.example.ProyectoFinal.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreoAndDocumento(
                request.getCorreo(), request.getDocumento()
        );

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // ✅ Generar token y guardar en la base de datos
            String token = tokenService.generarToken(usuario);
            LocalDateTime expiracion = tokenService.obtenerExpiracionDelToken(token);


            // ✅ Crear LoginResponse
            LoginResponse response = new LoginResponse(
                    usuario.getId(),
                    usuario.getRol(),
                    token,
                    expiracion
            );
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).build();
    }

}
