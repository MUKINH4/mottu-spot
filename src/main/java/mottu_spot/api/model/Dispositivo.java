package mottu_spot.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Builder.Default
    private boolean ativo = false;

    @OneToOne
    @JoinColumn(name = "moto_id")
    @JsonBackReference
    private Moto moto;
    
    public Dispositivo(String id, boolean ativo) {
        this.id = id;
        this.ativo = ativo;
    }
}
