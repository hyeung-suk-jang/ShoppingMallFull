package com.example.parayo.controller;

import com.example.parayo.common.ApiResponse;
import com.example.parayo.domain.auth.SignupService;
import com.example.parayo.domain.user.User;
import com.example.parayo.domain.user.UserService;
import com.example.parayo.dto.SignupRequest;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = "사용자 APIs")
public class UserApiController {
    private final UserService userService;
    private final SignupService signupService;

    @PostMapping("/users")
    public ApiResponse signup(@RequestBody SignupRequest signupRequest) {
        return ApiResponse.ok(signupService.signup(signupRequest));
    }

    @GetMapping("/users/{id}")
    public ApiResponse findUser(Authentication authentication, @PathVariable("id") Long id) {
        if (Objects.isNull(authentication)) {
            return ApiResponse.error("토큰 에러");
        }
        User user = userService.find(id);
        return ApiResponse.ok(user);
    }

    @PatchMapping("/users/fcm-token")
    public ApiResponse updateFcm(Authentication authentication, @RequestParam String fcmToken) {
        if (Objects.isNull(authentication)) {
            return ApiResponse.error("토큰 갱신 실패");
        }

        Claims claims = (Claims) authentication.getPrincipal();
        String email = claims.get("email", String.class);
        return ApiResponse.ok(userService.updateFcmToken(email, fcmToken));
    }
}
