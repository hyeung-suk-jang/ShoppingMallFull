package com.example.parayo.controller;

import com.example.parayo.common.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hcheck")
@Api(tags = "헬스체크 APIs")
public class HeathCheckController {

    @GetMapping
    @ApiOperation(value = "헬스체크 (API 토큰 필요없음)")
    public ApiResponse healthCheck() {
        return ApiResponse.ok((System.currentTimeMillis()));
    }
}
