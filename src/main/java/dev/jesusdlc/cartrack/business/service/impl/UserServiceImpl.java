package dev.jesusdlc.cartrack.business.service.impl;

import dev.jesusdlc.cartrack.business.service.UserService;
import dev.jesusdlc.cartrack.domain.entity.Usuario;
import dev.jesusdlc.cartrack.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public Optional<Usuario> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
