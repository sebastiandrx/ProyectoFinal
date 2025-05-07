package com.example.ProyectoFinal.Service;

import com.example.ProyectoFinal.Models.TokenLogin;
import com.example.ProyectoFinal.Repository.TokenLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenLoginService {

    @Autowired
    private TokenLoginRepository tokenRepo;

    public Optional<TokenLogin> getByToken(String token) {
        return tokenRepo.findByToken(token);
    }

    public TokenLogin save(TokenLogin tokenLogin) {
        return tokenRepo.save(tokenLogin);
    }


}

