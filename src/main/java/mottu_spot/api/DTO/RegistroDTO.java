package mottu_spot.api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistroDTO {
    
    @NotBlank(message = "O campo 'nome' é obrigatório.")
    private String nome;

    @NotBlank(message = "O campo 'usuario' é obrigatório.")
    private String usuario;

    @NotBlank(message = "O campo 'senha' é obrigatório.")
    private String senha;

    @NotBlank(message = "O campo 'confirmarSenha' é obrigatório.")
    private String confirmarSenha;
}
