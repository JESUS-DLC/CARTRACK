package dev.jesusdlc.cartrack.domain.specification;

import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.domain.entity.Services;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SearchServicesSpecification implements Specification<Services> {
    private Long carId;
    private BigInteger minCost;
    private BigInteger maxCost;
    private LocalDate date;
    private Boolean status;
    private String username;

    public SearchServicesSpecification(BigInteger minCost, BigInteger maxCost, LocalDate date, Boolean status, String username) {
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.date = date;
        this.status = status;
        this.username = username;
    }

    @Override
    public Predicate toPredicate(Root<Services> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Join<Services, Car> servicesCarJoin = root.join("car");
        if(carId!=null){
            Path<Long> carIdPath = servicesCarJoin.get("id");
            Predicate carPredicate = criteriaBuilder.equal(carIdPath,carId);
            predicates.add(carPredicate);
        }

        Join<Car, Usuario> carUsuarioJoin = servicesCarJoin.join("user");
        if(!username.isEmpty()){
            Path<String> userPath = carUsuarioJoin.get("username");
            Predicate userPredicate = criteriaBuilder.equal(userPath,username);
            predicates.add(userPredicate);
        }

        if(minCost!=null){
            Predicate minCostPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),minCost);
            predicates.add(minCostPredicate);
        }
        if(maxCost!=null){
            Predicate maxCostPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("cost"),maxCost);
            predicates.add(maxCostPredicate);
        }
        if(date!=null){
            Predicate datePredicate = criteriaBuilder.equal(root.get("date"),date);
            predicates.add(datePredicate);
        }
        if(status!=null){
            System.out.println(status);
            System.out.println(status);
            System.out.println(status);
            Predicate statusPredicate = criteriaBuilder.equal(root.get("status"),status);
            predicates.add(statusPredicate);
        }

        query.orderBy(criteriaBuilder.desc(root.get("date")));
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
