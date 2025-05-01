package mottu_spot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mottu_spot.api.DTO.PatioDTO;
import mottu_spot.api.model.Patio;
import mottu_spot.api.service.PatioService;
import mottu_spot.api.specification.PatioSpecification;



@RestController
@RequestMapping("/patio")
public class PatioController {

    public record PatioFilter(String nome, String bairro, String logradouro, String cidade, String estado, String pais) {
    }
    
    @Autowired
    private PatioService patioService;

    @Transactional
    @PostMapping
    @CacheEvict(value = "patios", allEntries = true)
    public ResponseEntity<PatioDTO> adicionarPatio(@Valid @RequestBody PatioDTO patioDto) {        
        patioService.criarPatio(patioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patioDto);

    }

    @GetMapping
    @Cacheable("patios")
    public Page<Patio> listarPatios(PatioFilter patioFilter, @PageableDefault(direction = Direction.ASC, size = 10, sort = "nome") Pageable pageable) {
        Specification<Patio> specification = PatioSpecification.withFilters(patioFilter);
        return patioService.listarPatios(specification, pageable);
    }
    
    

}
