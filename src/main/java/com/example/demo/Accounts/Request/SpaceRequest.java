package com.example.demo.Accounts.Request;

import lombok.Data;

@Data
public class SpaceRequest {
    private String userType;
    private String username;
    private String password;
    private String email;
    private String phone;
}
