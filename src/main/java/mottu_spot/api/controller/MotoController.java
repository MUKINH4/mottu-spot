package mottu_spot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import mottu_spot.api.DTO.MotoDTO;
import mottu_spot.api.model.Moto;
import mottu_spot.api.service.MotoService;


@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @PostMapping
    public ResponseEntity<Moto> adicionarMoto(@Valid @RequestBody MotoDTO motoDto) {
        Moto moto = motoService.adicionarMoto(motoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moto);
    }
    
}
