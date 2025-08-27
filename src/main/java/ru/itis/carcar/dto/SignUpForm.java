package ru.itis.carcar.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @Size(min = 8, max = 20)
    private String password;
}
