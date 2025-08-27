package ru.itis.carcar.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.carcar.model.User;
import ru.itis.carcar.dto.SignUpForm;
import ru.itis.carcar.repository.UserRepository;
import ru.itis.carcar.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUp(SignUpForm form) {
        if (userRepository.existsByEmail(form.getEmail()) || userRepository.existsByUsername(form.getUsername())) {
            throw new IllegalArgumentException("Email or username already exists");
        }

        User user = User.builder()
                .username(form.getUsername())
                .email(form.getEmail())
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();

        userRepository.save(user);
    }

    @Override
    public User findOrCreateOAuthUser(String sub, String email) {
        return userRepository.findOneByOauthId(sub)
                .orElseGet(() -> {
                    String finalUsername = generateRandomUsername();

                    User user = User.builder()
                            .oauthId(sub)
                            .email(email)
                            .username(finalUsername)
                            .role(User.Role.USER)
                            .state(User.State.ACTIVE)
                            .passwordHash(UUID.randomUUID().toString())
                            .build();

                    return userRepository.save(user);
                });
    }

    private String generateRandomUsername() {
        String username;
        do {
            int randomNumber = (int) (Math.random() * 900_000) + 100_000;
            username = "user" + randomNumber;
        } while (userRepository.existsByUsername(username));
        return username;
    }

}
