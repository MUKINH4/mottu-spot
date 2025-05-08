package mottu_spot.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mottu_spot.api.model.Dispositivo;
import mottu_spot.api.model.Moto;
import mottu_spot.api.repository.DispositivoRepository;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private MotoService motoService;

    public void mudarEstadoAlarme(Long motoId){
        Dispositivo dispositivo = dispositivoRepository.findByMotoId(motoId);
        if (dispositivo == null){
            throw new RuntimeException("Dispositivo não encontrado");
        }
        System.out.println("Antes: " + dispositivo.isAtivo());
        dispositivo.setAtivo(!dispositivo.isAtivo());
        dispositivoRepository.save(dispositivo);
        System.out.println("Depois: " + dispositivo.isAtivo());

    }

    public Dispositivo criarDispositivo(Long motoId){
        Moto moto = motoService.buscarMotoPorId(motoId)
            .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
        Dispositivo dispositivo = Dispositivo.builder()
        .ativo(false)
        .moto(moto)
        .build();
        return dispositivoRepository.save(dispositivo);
    }
}
