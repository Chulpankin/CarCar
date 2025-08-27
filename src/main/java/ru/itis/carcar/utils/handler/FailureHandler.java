package ru.itis.carcar.utils.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Slf4j
@Component
@ControllerAdvice
@RequiredArgsConstructor
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException {
        log.error("Authentication failed: {}", exception.getMessage(), exception);
        response.sendRedirect("/login?error");
    }

    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(HttpServletRequest request, ServiceException exception, Model model) {
        log.error("Handling ServiceException: {}", exception.getMessage(), exception);
        model.addAttribute("failedUrl", request.getRequestURL());
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(HttpServletRequest request, Exception exception, Model model) {
        log.error("Handling general Exception: {}", exception.getMessage(), exception);
        model.addAttribute("failedUrl", request.getRequestURL());
        model.addAttribute("errorMessage", "Sorry, an unexpected error has occurred.");
        return "error";
    }
}
