package ru.itis.carcar.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @Email
    @NotBlank
    private String email;

    @Size(min = 3, max = 20) @NotBlank
    private String username;

    @Size(min = 6, max = 100) @NotBlank
    private String password;
}

