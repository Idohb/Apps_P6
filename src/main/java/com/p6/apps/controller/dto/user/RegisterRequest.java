package com.p6.apps.controller.dto.user;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {
    @NotEmpty(message = "Veuillez tappez le prénom")
    private String first_name;
    @NotEmpty(message = "Veuillez tappez le nom")
    private String last_name;
    @NotEmpty(message = "Veuillez tappez l'email")
    private String email;
    @NotEmpty(message = "Veuillez tappez le mot de passe")
    private String password;
}
