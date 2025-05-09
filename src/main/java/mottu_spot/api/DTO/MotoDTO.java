package mottu_spot.api.DTO;

import mottu_spot.api.model.Dispositivo;

public record MotoDTO (
    Long id,
    String placa, 
    String descricao, 
    String status, 
    Long patioId,
    Dispositivo dispositivo
){}
