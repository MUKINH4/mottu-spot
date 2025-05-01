package mottu_spot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import mottu_spot.api.model.Patio;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long>, JpaSpecificationExecutor<Patio>{
    
}
