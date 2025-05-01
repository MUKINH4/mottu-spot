package mottu_spot.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import mottu_spot.api.DTO.PatioDTO;
import mottu_spot.api.model.Endereco;
import mottu_spot.api.model.Patio;
import mottu_spot.api.repository.EnderecoRepository;
import mottu_spot.api.repository.PatioRepository;

@Service
public class PatioService {

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Patio criarPatio(PatioDTO patioDto) {
        Endereco endereco = Endereco.builder()
        .logradouro(patioDto.getLogradouro())
        .numero(patioDto.getNumero())
        .cidade(patioDto.getCidade())
        .estado(patioDto.getEstado())
        .pais(patioDto.getPais())
        .cep(patioDto.getCep())
        .bairro(patioDto.getBairro())
        .build();

        enderecoRepository.save(endereco);

        Patio patio = Patio.builder()
        .nome(patioDto.getNome())
        .endereco(endereco)
        .build();
        return patioRepository.save(patio);
    }

    public List<Patio> listarPatios(){
        return patioRepository.findAll();
    }

    
    public Page<Patio> listarPatios(Specification<Patio> patioFilter, Pageable pageable){
        return patioRepository.findAll(patioFilter, pageable);
    }

    public Optional<Patio> buscarPatioPorId(Long id) {
        return patioRepository.findById(id);
    }
}
