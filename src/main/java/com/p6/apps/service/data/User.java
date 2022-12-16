package com.p6.apps.service.data;

import lombok.Data;

@Data
public class User {
    private Long idUser;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private double balance;
}
