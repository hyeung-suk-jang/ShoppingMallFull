package com.example.parayo.dto;

import lombok.*;


@Data
@EqualsAndHashCode
public class SigninRequest {
    private String email;
    private String password;
    private String fcmToken;

}
