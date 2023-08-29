package com.p6.apps.controller.dto.user;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    @NotEmpty(message = "Veuillez tapez l'email")
    private String email;
    @NotEmpty(message = "Veuillez tapez le mot de passe")
    private String password;
}
