package mottu_spot.api.DTO;

import lombok.Data;

@Data
public class MotoDTO {
    private String placa;
    private String descricao;
    private String status; // Deve ser "ATIVO" ou "INATIVO"
    private Long patioId;
}
