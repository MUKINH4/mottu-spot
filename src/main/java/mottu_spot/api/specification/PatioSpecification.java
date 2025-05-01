package mottu_spot.api.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import mottu_spot.api.controller.PatioController.PatioFilter;
import mottu_spot.api.model.Endereco;
import mottu_spot.api.model.Patio;

public class PatioSpecification {
    public static Specification<Patio> withFilters(PatioFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nome() != null && !filter.nome().isBlank()) {
                predicates.add(
                    cb.like(cb.lower(root.get("nome")), "%" + filter.nome().toLowerCase() + "%")
                );
            }

             boolean usarEndereco = 
                (filter.logradouro() != null && !filter.logradouro().isBlank()) ||
                (filter.bairro() != null && !filter.bairro().isBlank()) ||
                (filter.cidade() != null && !filter.cidade().isBlank()) ||
                (filter.estado() != null && !filter.estado().isBlank()) ||
                (filter.pais() != null && !filter.pais().isBlank());

            Join<Patio, Endereco> enderecoJoin = null;
            if (usarEndereco) {
                enderecoJoin = root.join("endereco");
            }

            if (enderecoJoin != null) {
                if (filter.logradouro() != null && !filter.logradouro().isBlank()) {
                    predicates.add(
                        cb.like(cb.lower(enderecoJoin.get("logradouro")), "%" + filter.logradouro().toLowerCase() + "%")
                    );
                }

                if (filter.bairro() != null && !filter.bairro().isBlank()) {
                    predicates.add(
                        cb.like(cb.lower(enderecoJoin.get("bairro")), "%" + filter.bairro().toLowerCase() + "%")
                    );
                }

                if (filter.cidade() != null && !filter.cidade().isBlank()) {
                    predicates.add(
                        cb.like(cb.lower(enderecoJoin.get("cidade")), "%" + filter.cidade().toLowerCase() + "%")
                    );
                }

                if (filter.estado() != null && !filter.estado().isBlank()) {
                    predicates.add(
                        cb.like(cb.lower(enderecoJoin.get("estado")), "%" + filter.estado().toLowerCase() + "%")
                    );
                }

                if (filter.pais() != null && !filter.pais().isBlank()) {
                    predicates.add(
                        cb.like(cb.lower(enderecoJoin.get("pais")), "%" + filter.pais().toLowerCase() + "%")
                    );
                }
            }

            var arrayPredicates = predicates.toArray(Predicate[]::new);
            return cb.and(arrayPredicates);
        };

    }
}
