package mottu_spot.api.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import mottu_spot.api.controller.MotoController.MotoFilter;
import mottu_spot.api.model.Moto;

public class MotoSpecification {
    public static Specification<Moto> withFilters(MotoFilter filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.placa() != null && !filters.placa().isBlank()) {
                predicates.add(
                    cb.like(cb.lower(root.get("placa")), "%" + filters.placa().toLowerCase() + "%")
                );
            }
            if (filters.status() != null && !filters.status().isBlank()) {
                predicates.add(
                    cb.equal(cb.lower(root.get("status")), filters.status().toLowerCase())
                );
            }
            
            Predicate[] arrayPredicates = predicates.toArray(Predicate[]::new);
            return cb.and(arrayPredicates);
        };

    }
}
