package mottu_spot.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    
    @NotBlank(message = "logradouro não pode ser nulo")
    private String logradouro;
    
    private int numero;

    @NotBlank(message = "bairro não pode ser nulo")
    private String bairro;

    @NotBlank(message = "cidade não pode ser nulo")
    private String cidade;

    @NotBlank(message = "estado não pode ser nulo")
    private String estado;

    @NotBlank(message = "país não pode ser nulo")
    private String pais;

}
