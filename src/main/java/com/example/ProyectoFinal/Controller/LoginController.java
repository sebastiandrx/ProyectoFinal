package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.LoginRequest;
import com.example.ProyectoFinal.Models.LoginResponse;
import com.example.ProyectoFinal.Models.TokenLogin;
import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import com.example.ProyectoFinal.Service.LoginService;
import com.example.ProyectoFinal.Service.TokenLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return loginService.login(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

}



