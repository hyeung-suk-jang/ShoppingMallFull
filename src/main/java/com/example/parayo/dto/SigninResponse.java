package com.example.parayo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
public class SigninResponse {
    private String token;
    private String refreshToken;
    private String userName;
    private Long userId;

    public SigninResponse(String token, String refreshToken, String userName, Long userId) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userName = userName;
        this.userId = userId;
    }
}
