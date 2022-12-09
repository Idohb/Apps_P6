package com.p6.apps.controller.dto.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private double balance;
}
