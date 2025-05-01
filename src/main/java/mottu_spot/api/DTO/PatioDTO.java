package mottu_spot.api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatioDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O logradouro é obrigatório.")
    private String logradouro;

    private int numero;

    @NotBlank(message = "A cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "O bairro é obrigatório.")
    private String bairro;

    @NotBlank(message = "O país é obrigatório.")
    private String pais;

    @NotBlank(message = "O estado é obrigatório.")
    private String estado;

    private String cep;
}