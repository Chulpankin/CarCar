package ru.itis.carcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.carcar.security.UserDetailsImpl;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("name", userDetails.getUser().getUsername());
        model.addAttribute("email", userDetails.getUser().getEmail());
        return "profile";
    }
}
