package ru.itis.carcar.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.itis.carcar.dto.SignUpForm;
import ru.itis.carcar.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("form", new SignUpForm());
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUpUser(
            @Valid @ModelAttribute("form") SignUpForm signUpForm,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.groupingBy(
                            FieldError::getField,
                            Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                    ));
            model.addAttribute("errors", errors);
            return "sign_up";
        }

        try {
            userService.signUp(signUpForm);
            return "redirect:/signIn";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "sign_up";
        }
    }

}
