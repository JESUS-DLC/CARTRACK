package dev.jesusdlc.cartrack.business.service;

import dev.jesusdlc.cartrack.domain.entity.Usuario;

import java.util.Optional;

public interface UserService {

    Optional<Usuario> findByUsername(String username);
}
