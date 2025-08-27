package ru.itis.carcar.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.itis.carcar.model.UserDto;
import ru.itis.carcar.model.User;
import ru.itis.carcar.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService
        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private static final String ADMIN_EMAIL = "admin@gmail.com";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest req) {
        OAuth2User oauth = new DefaultOAuth2UserService().loadUser(req);
        String email = oauth.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .email(email)
                                .username(email.split("@")[0])
                                .passwordHash("") // не нужен
                                .role(email.equals(ADMIN_EMAIL)
                                        ? UserDto.Role.ADMIN : UserDto.Role.USER)
                                .state(UserDto.State.ACTIVE)
                                .build()
                ));

        return new DefaultOAuth2User(
                Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
                ),
                oauth.getAttributes(),
                "email"
        );
    }
}
