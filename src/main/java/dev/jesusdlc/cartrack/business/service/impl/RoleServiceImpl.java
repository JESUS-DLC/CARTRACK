package dev.jesusdlc.cartrack.business.service.impl;

import dev.jesusdlc.cartrack.business.service.RoleService;
import dev.jesusdlc.cartrack.domain.entity.Role;
import dev.jesusdlc.cartrack.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName("CUSTOMER");
    }
}
