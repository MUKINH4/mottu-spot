package mottu_spot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mottu_spot.api.model.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, String> {
    Dispositivo findByMotoId(Long motoId);    
}
