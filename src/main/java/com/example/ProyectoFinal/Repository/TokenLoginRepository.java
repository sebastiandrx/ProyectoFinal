package com.example.ProyectoFinal.Repository;

import com.example.ProyectoFinal.Models.TokenLogin;
import com.example.ProyectoFinal.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenLoginRepository extends JpaRepository<TokenLogin, UUID> {
    Optional<TokenLogin> findByToken(String token);
    Optional<TokenLogin> findByUsuario(Usuario usuario);

}
