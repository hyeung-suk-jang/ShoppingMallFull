package com.example.parayo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@Getter
@EqualsAndHashCode
public class SignupRequest {
    private String email;
    private String name;
    private String password;
    private String fcmToken;
}
