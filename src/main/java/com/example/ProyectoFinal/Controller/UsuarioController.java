package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.Models.Equipo;
import com.example.ProyectoFinal.Models.Usuario;
import com.example.ProyectoFinal.Repository.EquipoRepository;
import com.example.ProyectoFinal.Repository.UsuarioRepository;
import com.example.ProyectoFinal.Service.QrService;
import com.example.ProyectoFinal.Service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private QrService qrService;

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

    @GetMapping("/{id}/qr")
    public ResponseEntity<byte[]> generarQr(@PathVariable UUID id) throws Exception {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id); // asegúrate de tener este método
        Equipo equipo = equipoRepo.findByUsuarioId(id).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Map<String, String> contenido = new HashMap<>();
        contenido.put("usuarioId", usuario.getId().toString());
        contenido.put("equipoId", equipo.getId().toString());

        String json = new ObjectMapper().writeValueAsString(contenido);
        byte[] qrBytes = qrService.generarQr(json);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrBytes);
    }

}