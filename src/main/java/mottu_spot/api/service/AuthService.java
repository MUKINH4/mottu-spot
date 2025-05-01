package mottu_spot.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mottu_spot.api.DTO.LoginDTO;
import mottu_spot.api.DTO.RegistroDTO;
import mottu_spot.api.model.Usuario;
import mottu_spot.api.repository.AuthRepository;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario registrar(RegistroDTO registroDTO) {
        if (!registroDTO.getSenha().equals(registroDTO.getConfirmarSenha())) {
            throw new IllegalArgumentException("As senhas não coincidem");
        }

        Usuario usuario = Usuario.builder()
                .nome(registroDTO.getNome())
                .senha(passwordEncoder.encode(registroDTO.getSenha()))
                .usuario(registroDTO.getUsuario())
                .build();

        
        return authRepository.save(usuario);
    }

    public Usuario logar(LoginDTO loginDTO) {
        Usuario usuario = authRepository.findByUsuario(loginDTO.getUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        return usuario; // Retorna o usuário autenticado
    }
}
