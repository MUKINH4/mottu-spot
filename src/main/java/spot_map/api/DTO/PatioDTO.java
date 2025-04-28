package spot_map.api.DTO;

import lombok.Data;

@Data
public class PatioDTO {

    private String nome;

    private String logradouro;
    private int numero;
    private String cidade;
    private String bairro;
    private String pais;
    private String estado;
    private String cep;
}

