package ru.itis.carcar.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SignInController {

    @Value("${google.client-id}")
    private String googleClientId;

    @GetMapping("/signIn")
    public String getSignInPage(HttpServletRequest request, ModelMap model) {
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", "error");
        }
        if (request.getParameterMap().containsKey("oauthError")) {
            model.addAttribute("oauthError", request.getParameter("oauthError"));
        }
        model.addAttribute("googleClientId", googleClientId);
        return "sign_in";
    }

}
