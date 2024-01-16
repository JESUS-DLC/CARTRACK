package dev.jesusdlc.cartrack.persistence.repository;

import dev.jesusdlc.cartrack.domain.entity.Car;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long>, JpaSpecificationExecutor<Car> {
    Boolean existsByIdAndUserUsername(long id,String username);
    Optional<Car> findByIdAndUserUsername(long id,String username);
}
