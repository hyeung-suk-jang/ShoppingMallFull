package com.example.parayo.common;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class ParayoExceptionHandler {
    private static final Logger logger = LogManager.getLogger(ParayoExceptionHandler.class);

    @ExceptionHandler(ParayoException.class)
    public ApiResponse handleParayoException(ParayoException e) {
        logger.error("API error", e);
        return ApiResponse.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(ExceptionHandler e) {
        logger.error("API error", e);
        return ApiResponse.error("알 수 없는 오류가 발생했습니다.");
    }

}


