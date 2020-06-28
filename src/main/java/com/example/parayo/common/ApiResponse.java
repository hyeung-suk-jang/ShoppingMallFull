package com.example.parayo.common;

import com.example.parayo.global.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    private ApiResponse(boolean response, T data) {
        this.success = response;
        this.data = data;
        this.message = "";
    }

    private ApiResponse(boolean response, String message) {
        this.success = response;
        this.data = null;
        this.message = message;
    }

    public static <T> ApiResponse ok(T data) {
        return new ApiResponse(true, data);
    }

    public static ApiResponse error(String message) {
        if (StringUtils.isBlank(message)) {
            new ApiResponse(false, "");
        }
        return new ApiResponse(false, message);
    }

}
