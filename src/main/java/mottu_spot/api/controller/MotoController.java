package mottu_spot.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mottu_spot.api.DTO.MotoDTO;
import mottu_spot.api.model.Dispositivo;
import mottu_spot.api.model.Moto;
import mottu_spot.api.service.MotoService;
import mottu_spot.api.specification.MotoSpecification;


@RestController
@RequestMapping("/moto")
public class MotoController {

    private MotoDTO toResponseDTO(Moto moto) {
        Dispositivo dispositivo = null;
    if (moto.getDispositivo() != null) {
        dispositivo = new Dispositivo(
            moto.getDispositivo().getId(),
            moto.getDispositivo().isAtivo()
        );
    }
    return new MotoDTO(
        moto.getId(),
        moto.getPlaca(),
        moto.getDescricao(),
        moto.getStatus().name(),
        moto.getPatio() != null ? moto.getPatio().getId() : null,
        dispositivo
        );
    }

    public record MotoFilter(String placa, String status) {
    }

    @Autowired
    private MotoService motoService;

    @PostMapping
    @CacheEvict(value = "motos", allEntries = true)
    public ResponseEntity<Moto> adicionarMoto(@Valid @RequestBody MotoDTO motoDto) {
        Moto moto = motoService.adicionarMoto(motoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moto);
    }

    @GetMapping
    public Page<MotoDTO> listarMotos(MotoFilter motoFilter, @PageableDefault(size = 10) Pageable pageable) {
    Specification<Moto> specification = MotoSpecification.withFilters(motoFilter);
    return motoService.listarMotos(specification, pageable)
        .map(this::toResponseDTO);
}

    @GetMapping("/patio/{patioId}")
    @Cacheable("motos")
    public Page<Moto> listarMotosPorPatio(@PathVariable Long patioId, MotoFilter motoFilter, @PageableDefault(size = 10) Pageable pageable ) {
    Specification<Moto> specification = Specification
        .where(MotoSpecification.withFilters(motoFilter))
        .and((root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("patio").get("id"), patioId)
        );

    return motoService.listarMotos(specification, pageable);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "motos", key = "#id")
    public ResponseEntity<Optional<Moto>> buscarMotoPorId(@PathVariable Long id){
        if (!motoService.buscarMotoPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(motoService.buscarMotoPorId(id));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "motos", allEntries = true)
    public ResponseEntity<Void> deletarMoto(@PathVariable Long id){
        if(!motoService.buscarMotoPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        motoService.deletarMoto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "motos", allEntries = true)
    public ResponseEntity<Moto> atualizarMoto(@PathVariable Long id, @RequestBody MotoDTO motoDto){
        if (!motoService.buscarMotoPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        Moto moto = motoService.atualizarMoto(id, motoDto);
        return ResponseEntity.ok(moto);
    }
    
}
