package dev.jesusdlc.cartrack.domain.specification;

import dev.jesusdlc.cartrack.domain.entity.Brand;
import dev.jesusdlc.cartrack.domain.entity.Car;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SearchCarSpecification implements Specification<Car> {

    private Long brandId;
    private Long year;

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Join<Car, Brand> carBrandJoin = root.join("brand");
        if(brandId!=null){
            Path<Long> brandIdPath = carBrandJoin.get("id");
            Predicate brandPredicate = criteriaBuilder.equal(brandIdPath,brandId);
            predicates.add(brandPredicate);
        }

        if(year!= null){
            Predicate yearPredicate = criteriaBuilder.equal(root.get("year"),year);
            predicates.add(yearPredicate);
        }

        query.orderBy(criteriaBuilder.asc(root.get("model")));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
