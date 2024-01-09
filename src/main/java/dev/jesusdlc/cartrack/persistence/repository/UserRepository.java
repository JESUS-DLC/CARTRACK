package dev.jesusdlc.cartrack.persistence.repository;

import dev.jesusdlc.cartrack.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);
}
