package ru.itis.carcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.carcar.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findOneByOauthId(String oauthId);
}
