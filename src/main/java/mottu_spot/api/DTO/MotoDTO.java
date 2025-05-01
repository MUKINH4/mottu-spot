package mottu_spot.api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MotoDTO {

    @NotBlank(message = "O campo 'placa' é obrigatório.")
    private String placa;

    @NotBlank(message = "O campo 'descricao' é obrigatório.")
    private String descricao;

    @NotBlank(message = "O campo 'status' é obrigatório.")
    private String status;

    private Long patioId;
}
