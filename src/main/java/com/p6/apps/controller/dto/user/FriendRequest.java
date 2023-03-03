package com.p6.apps.controller.dto.user;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FriendRequest {
    @NotNull
    private Long userCurrent;
    @NotEmpty(message = "Il faut l'email de votre ami")
    private String email;
}
