package ru.itis.carcar.model.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    @Email
    @NotBlank(message = "Email cannot be empty")
    @Pattern(
            regexp = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]{2,}",
            message = "Email address does not match the format - mail@example.com"
    )
    private String email;

    @Pattern(
            regexp =
                    "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}",
            message = "Password must contain at least 8 characters, 1 digit, 1 lowercase and 1 uppercase letter"
    )
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3, max = 20, message = "First name must be between 3 and 20 characters")
    private String username;
}