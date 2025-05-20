package mottu_spot.api.model;

import java.time.LocalDateTime;

import mottu_spot.api.model.enums.Status;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 6, max = 10, message = "A placa deve ter entre 6 e 10 caracteres")
    @Pattern(regexp = "^[A-Z0-9\\- ]{6,10}$", message = "Placa fora do padrão")
    private String placa;

    @Size(max = 500, message = "O máximo de caracteres é 500")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "status não pode ser nulo")
    private Status status;

    @Builder.Default
    private LocalDateTime dataAdicao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "patio_id")
    @JsonBackReference
    private Patio patio;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference
    private Dispositivo dispositivo;
}
