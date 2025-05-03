package mottu_spot.api.DTO;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mottu_spot.api.model.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDTO {

    @NotBlank(message = "O campo 'usuario' é obrigatório.")
    private String usuario;

    @NotBlank(message = "O campo 'senha' é obrigatório.")
    private String senha;

    @NotBlank(message = "O campo 'confirmarSenha' é obrigatório.")
    private String confirmarSenha;

    @Enumerated(EnumType.STRING)
    private Role role;
}
