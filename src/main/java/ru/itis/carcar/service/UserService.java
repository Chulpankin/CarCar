package ru.itis.carcar.service;

import ru.itis.carcar.model.User;
import ru.itis.carcar.dto.SignUpForm;

public interface UserService {

    void signUp(SignUpForm form);

    User findOrCreateOAuthUser(String sub, String email);
}
