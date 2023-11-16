package dev.jesusdlc.cartrack.persistence.repository;

import dev.jesusdlc.cartrack.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Boolean existsByName(String name);
    Boolean existsById(long id);
}
