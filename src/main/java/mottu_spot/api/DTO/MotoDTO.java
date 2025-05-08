package mottu_spot.api.DTO;

public record MotoDTO (
    Long id,
    String placa, 
    String descricao, 
    String status, 
    Long patioId
){}
