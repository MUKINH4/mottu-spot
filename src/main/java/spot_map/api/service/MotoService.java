package spot_map.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spot_map.api.DTO.MotoDTO;
import spot_map.api.model.Moto;
import spot_map.api.model.Patio;
import spot_map.api.repository.MotoRepository;
import spot_map.api.repository.PatioRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private PatioRepository patioRepository;

    public Moto adicionarMoto(MotoDTO motoDto) {
        Patio patio = patioRepository.findById(motoDto.getPatioId())
            .orElseThrow(() -> new RuntimeException());

        Moto moto = Moto.builder()
        .placa(motoDto.getPlaca())
        .descricao(motoDto.getDescricao())
        .status(Moto.Status.valueOf(motoDto.getStatus().toUpperCase()))
        .patio(patio)
        .build();

        return motoRepository.save(moto);
    }
}