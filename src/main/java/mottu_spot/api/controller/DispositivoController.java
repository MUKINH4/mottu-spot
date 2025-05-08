package mottu_spot.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mottu_spot.api.DTO.DispositivoDTO;
import mottu_spot.api.model.Dispositivo;
import mottu_spot.api.service.DispositivoService;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;

    @PostMapping
    public ResponseEntity<Dispositivo> criarDispositivo(@RequestBody DispositivoDTO dto){
        return ResponseEntity.ok(dispositivoService.criarDispositivo(dto.motoId()));
    }

    @PutMapping("/alarme/{motoId}")
    public void mudarEstadoAlarme(@PathVariable Long motoId){
        dispositivoService.mudarEstadoAlarme(motoId);
    }
}
