package mottu_spot.api.DTO;

public record PatioDTO (
    String nome, 
    String logradouro, 
    int numero, 
    String cidade, 
    String bairro, 
    String pais, 
    String estado, 
    String cep
){}