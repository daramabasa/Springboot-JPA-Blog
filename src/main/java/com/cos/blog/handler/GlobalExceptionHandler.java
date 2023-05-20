package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 Exception을 받아옴
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseDTO<String> handleArgumentException(IllegalArgumentException e){
        return new ResponseDTO<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        //return "<h1>" + e.getMessage() + "</h1>";
    }
}
