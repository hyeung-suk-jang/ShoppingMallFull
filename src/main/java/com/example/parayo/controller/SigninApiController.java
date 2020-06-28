package com.example.parayo.controller;

import com.example.parayo.common.ApiResponse;
import com.example.parayo.domain.auth.SigninService;
import com.example.parayo.dto.SigninRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "인증 APIs")
@RequestMapping("/api/v1")
public class SigninApiController {
    private final SigninService signinService;

    @PostMapping("/signin")
    @ApiOperation(value = "사용자 로그인 (API 토큰 필요없음)")
    public ApiResponse signin(@RequestBody SigninRequest signinRequest) {
        return ApiResponse.ok(signinService.signin(signinRequest));
    }
}
