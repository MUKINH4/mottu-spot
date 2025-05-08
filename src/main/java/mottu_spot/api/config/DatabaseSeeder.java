package mottu_spot.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mottu_spot.api.model.Endereco;
import mottu_spot.api.model.Moto;
import mottu_spot.api.model.Patio;
import mottu_spot.api.model.enums.Status;
import mottu_spot.api.repository.EnderecoRepository;
import mottu_spot.api.repository.MotoRepository;
import mottu_spot.api.repository.PatioRepository;

@Component
public class DatabaseSeeder {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private PatioRepository patioRepository;


    @PostConstruct
    public void init(){
        var enderecos = List.of(
            Endereco.builder()
            .logradouro("Rua Principal")
            .numero(100)
            .cidade("São Paulo")
            .bairro("Centro")
            .pais("Brasil")
            .estado("SP")
            .cep("12345-678")
            .build(),

            Endereco.builder()
            .logradouro("Rua Secundaria")
            .numero(2)
            .cidade("São Paulo")
            .bairro("Jabaquara")
            .pais("Brasil")
            .estado("SP")
            .cep("32813-343")
            .build(),

            Endereco.builder()
            .logradouro("Rua Esperança")
            .numero(7)
            .cidade("Rio de Janeiro")
            .bairro("Centro")
            .pais("Brasil")
            .estado("RJ")
            .cep("43929-531")
            .build()
        );
        enderecoRepository.saveAll(enderecos);

        var patios = List.of(
            Patio.builder()
            .nome("Patio 1")
            .endereco(enderecos.get(0))
            .build(),

            Patio.builder()
            .nome("Patio 2")
            .endereco(enderecos.get(1))
            .build(),

            Patio.builder()
            .nome("Patio 3")
            .endereco(enderecos.get(2))
            .build()
        );

        patioRepository.saveAll(patios);

        var motos = List.of(
            Moto.builder()
            .placa("ABC1234")
            .descricao("Moto 1 - Nova")
            .status(Status.ATIVO)
            .patio(patios.get(0))
            .build(),

            Moto.builder()
            .placa("XYZ5678")
            .descricao("Moto 2 - Usada")
            .status(Status.INATIVO)
            .patio(patios.get(1))
            .build(),

            Moto.builder()
            .placa("DEF9012")
            .descricao("Moto 3 - Revisada")
            .status(Status.ATIVO)
            .patio(patios.get(2))
            .build()
        );

        motoRepository.saveAll(motos);

    }
}
