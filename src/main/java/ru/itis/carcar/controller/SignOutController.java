package ru.itis.carcar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SignOutController {

    @GetMapping("/signOut")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.logout();
            request.getSession().invalidate();

            var cookie = new Cookie("JSESSIONID", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/signIn";
        } catch (ServletException e) {
            log.error(e.getMessage());
            return "redirect:/error";
        }
    }

}
