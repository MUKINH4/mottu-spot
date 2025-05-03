package mottu_spot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mottu_spot.api.DTO.MotoDTO;
import mottu_spot.api.model.Moto;
import mottu_spot.api.service.MotoService;
import mottu_spot.api.specification.MotoSpecification;


@RestController
@RequestMapping("/moto")
public class MotoController {

    public record MotoFilter(String placa, String status) {
    }

    @Autowired
    private MotoService motoService;

    @PostMapping
    public ResponseEntity<Moto> adicionarMoto(@Valid @RequestBody MotoDTO motoDto) {
        Moto moto = motoService.adicionarMoto(motoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moto);
    }

    @GetMapping
    @Cacheable("motos")
    public Page<Moto> listarMotos(MotoFilter motoFilter, @PageableDefault(size = 10) Pageable pageable) {
        Specification<Moto> specification = MotoSpecification.withFilters(motoFilter);
        return motoService.listarMotos(specification, pageable);
    }
    
}
