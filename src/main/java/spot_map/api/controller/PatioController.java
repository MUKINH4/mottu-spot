package spot_map.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import spot_map.api.DTO.PatioDTO;
import spot_map.api.model.Patio;
import spot_map.api.service.PatioService;



@RestController
@RequestMapping("/patio")
public class PatioController {
    
    @Autowired
    private PatioService patioService;

    @Transactional
    @PostMapping
    public ResponseEntity<PatioDTO> adicionarPatio(@Valid @RequestBody PatioDTO patioDto) {        
        patioService.criarPatio(patioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patioDto);

    }

    @GetMapping
    public List<Patio> listarPatios() {
        return patioService.listarPatios();
    }
    
    

}
