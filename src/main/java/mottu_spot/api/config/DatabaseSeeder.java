package mottu_spot.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import mottu_spot.api.model.Dispositivo;
import mottu_spot.api.model.Endereco;
import mottu_spot.api.model.Moto;
import mottu_spot.api.model.Patio;
import mottu_spot.api.model.enums.Status;
import mottu_spot.api.repository.DispositivoRepository;
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

    @Autowired
    private DispositivoRepository dispositivoRepository;


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
            .logradouro("Rua dos Cachimbos")
            .numero(1010)
            .cidade("Cidade do México")
            .bairro("Centro")
            .pais("México")
            .estado("Estado do México")
            .cep("12345-678")
            .build(),

            Endereco.builder()
            .logradouro("Rua da Bagunça")
            .numero(1)
            .cidade("Itapeva")
            .bairro("Centro")
            .pais("Brasil")
            .estado("SP")
            .cep("48273-123")
            .build(),

            Endereco.builder()
            .logradouro("Rua das Flores")
            .numero(48)
            .cidade("Fortaleza")
            .bairro("Aldeota")
            .pais("Brasil")
            .estado("CE")
            .cep("12331-192")
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
            .build(),
            
            Patio.builder()
            .nome("Patio 4")
            .endereco(enderecos.get(3))
            .build(),

            Patio.builder()
            .nome("Patio 5")
            .endereco(enderecos.get(4))
            .build()
        );

        patioRepository.saveAll(patios);

        var motos = List.of(
            Moto.builder()
            .placa("ABC1234")
            .descricao("Nova")
            .status(Status.ATIVO)
            .patio(patios.get(0))
            .build(),

            Moto.builder()
            .placa("XYZ5678")
            .descricao("Usada")
            .status(Status.INATIVO)
            .patio(patios.get(0))
            .build(),

            Moto.builder()
            .placa("DEF9012")
            .descricao("Revisada")
            .status(Status.ATIVO)
            .patio(patios.get(2))
            .build(),

            Moto.builder()
            .placa("DQZ1521")
            .descricao("Boa para Uso")
            .status(Status.ATIVO)
            .patio(patios.get(3))
            .build(),

            Moto.builder()
            .placa("JKL3456")
            .descricao("Ótima")
            .status(Status.ATIVO)
            .patio(patios.get(4))
            .build()
        );

        motoRepository.saveAll(motos);

        var dispositivos = List.of(
            Dispositivo.builder()
                .moto(motos.get(0))
                .build(),

            Dispositivo.builder()
                .moto(motos.get(1))
                .build(),
            
            Dispositivo.builder()
                .moto(motos.get(2))
                .build(),

            Dispositivo.builder()
                .moto(motos.get(3))
                .build(),

            Dispositivo.builder()
                .moto(motos.get(4))
                .build()
        );
        dispositivoRepository.saveAll(dispositivos);
    };
}
