package ru.itis.carcar.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.carcar.model.User;
import ru.itis.carcar.security.UserDetailsImpl;
import ru.itis.carcar.service.GoogleOAuth2Service;
import ru.itis.carcar.service.UserService;

import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class OpenIdAuthController {

    private final GoogleOAuth2Service googleOAuth2Service;
    private final UserService userService;

    @GetMapping("/auth/google")
    public String redirectToGoogle() {
        String state = UUID.randomUUID().toString();
        String nonce = UUID.randomUUID().toString();
        String url = googleOAuth2Service.buildAuthUrl(state, nonce);
        return "redirect:" + url;
    }

    @GetMapping("/oauth2/callback")
    public String handleCallback(@RequestParam("code") String code, HttpServletRequest request) throws Exception {
        JsonNode tokenResponse = googleOAuth2Service.exchangeCodeForTokens(code);
        String idToken = tokenResponse.get("id_token").asText();
        Map<String, Object> claims = googleOAuth2Service.decodeIdToken(idToken);

        String email = (String) claims.get("email");
        String sub = (String) claims.get("sub");

        User user = userService.findOrCreateOAuthUser(sub, email);
        UserDetailsImpl principal = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return "redirect:/profile";
    }

}