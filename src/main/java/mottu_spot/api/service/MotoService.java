package mottu_spot.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import mottu_spot.api.DTO.MotoDTO;
import mottu_spot.api.model.Moto;
import mottu_spot.api.model.Patio;
import mottu_spot.api.model.enums.Status;
import mottu_spot.api.repository.MotoRepository;
import mottu_spot.api.repository.PatioRepository;

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
        .status(Status.valueOf(motoDto.getStatus().toUpperCase()))
        .patio(patio)
        .build();

        return motoRepository.save(moto);
    }

    public Optional<Moto> buscarMotoPorId(Long id) {
        return motoRepository.findById(id);
    }

    public Page<Moto> listarMotos(Specification<Moto> motoFilter, Pageable pageable) {
        return motoRepository.findAll(motoFilter, pageable);
    }
}