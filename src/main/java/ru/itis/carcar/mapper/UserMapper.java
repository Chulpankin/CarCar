package ru.itis.carcar.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.carcar.model.User;
import ru.itis.carcar.model.UserDto;
import ru.itis.carcar.model.form.SignUpForm;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;


    public UserDto toDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .state(user.getState())
                .build();
    }

    public User formToEntity(SignUpForm signUpForm) {
        return User.builder()
                .email(signUpForm.getEmail())
                .username(signUpForm.getUsername())
                .passwordHash(passwordEncoder.encode(signUpForm.getPassword()))
                .role(UserDto.Role.USER)
                .state(UserDto.State.ACTIVE)
                .build();
    }
}
