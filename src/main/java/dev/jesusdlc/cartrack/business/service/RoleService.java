package dev.jesusdlc.cartrack.business.service;

import dev.jesusdlc.cartrack.domain.entity.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findDefaultRole();
}
