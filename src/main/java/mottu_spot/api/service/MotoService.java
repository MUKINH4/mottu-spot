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

        Patio patio = patioRepository.findById(motoDto.patioId())
            .orElseThrow(() -> new RuntimeException());

        Moto moto = Moto.builder()
        .placa(motoDto.placa())
        .descricao(motoDto.descricao())
        .status(Status.valueOf(motoDto.status().toUpperCase()))
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

    public void deletarMoto(Long id) {
        motoRepository.deleteById(id);
    }

    public Moto atualizarMoto(Long id, MotoDTO motoDto) {
        Moto moto = motoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Moto não encontrada"));
        Patio patio = patioRepository.findById(motoDto.patioId()).orElseThrow(() -> new RuntimeException());

        moto.setDescricao(motoDto.descricao());
        moto.setPlaca(motoDto.placa());
        moto.setStatus(Status.valueOf(motoDto.status().toUpperCase()));
        moto.setPatio(patio);
        
        return motoRepository.save(moto);
    }

    
    
}
