package mottu_spot.api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "O campo usuário é obrigatório")
    private String usuario;

    @NotBlank(message = "O campo senha é obrigatório")
    private String senha;
}
