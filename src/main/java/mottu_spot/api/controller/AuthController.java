package mottu_spot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mottu_spot.api.DTO.LoginDTO;
import mottu_spot.api.DTO.LoginResponseDTO;
import mottu_spot.api.DTO.RegistroDTO;
import mottu_spot.api.config.TokenService;
import mottu_spot.api.model.Usuario;
import mottu_spot.api.repository.UsuarioRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/logar")
    public ResponseEntity<LoginResponseDTO> logar(@RequestBody @Valid LoginDTO loginDTO ){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getUsuario(), loginDTO.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody @Valid RegistroDTO registroDTO) {
        if (usuarioRepository.existsByUsuario(registroDTO.getUsuario())) throw new IllegalArgumentException("O nome do usuário já está em uso.");
        if (!registroDTO.getSenha().equals(registroDTO.getConfirmarSenha())) throw new IllegalArgumentException("As senhas não coincidem.");
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(registroDTO.getSenha());

        Usuario usuario = new Usuario(
            registroDTO.getUsuario(), encryptedPassword, registroDTO.getRole()
        );

        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
