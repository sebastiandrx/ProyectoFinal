package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.EquipoRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import com.example.ProyectoFinal.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EquipoRepository equipoRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.createUsuario(usuario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable UUID id) {
        return usuarioRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuarioActualizado) {
        return usuarioRepo.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombre(usuarioActualizado.getNombre());
                    usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
                    usuarioExistente.setDocumento(usuarioActualizado.getDocumento());
                    usuarioExistente.setRol(usuarioActualizado.getRol());
                    return ResponseEntity.ok(usuarioRepo.save(usuarioExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable UUID id) {
        return usuarioRepo.findById(id)
                .map(usuario -> {
                    usuarioRepo.delete(usuario);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}