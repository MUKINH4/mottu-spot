package mottu_spot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mottu_spot.api.DTO.LoginDTO;
import mottu_spot.api.DTO.RegistroDTO;
import mottu_spot.api.model.Usuario;
import mottu_spot.api.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public record UsuarioResponseDTO(
        Long id,
        String usuario,
        String nome,
        String role
    ) {}


    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@RequestBody @Valid RegistroDTO registroDTO){
        Usuario usuario = authService.registrar(registroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioResponseDTO> logar(@RequestBody @Valid LoginDTO loginDTO){
        Usuario usuario = authService.logar(loginDTO);
        UsuarioResponseDTO response = new UsuarioResponseDTO(
        usuario.getId(), usuario.getUsuario(), usuario.getNome(), usuario.getRole().name()
    );
    return ResponseEntity.ok(response);
    }
}
