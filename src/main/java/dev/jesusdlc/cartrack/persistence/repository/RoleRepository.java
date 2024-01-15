package dev.jesusdlc.cartrack.persistence.repository;

import dev.jesusdlc.cartrack.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String role);
}
