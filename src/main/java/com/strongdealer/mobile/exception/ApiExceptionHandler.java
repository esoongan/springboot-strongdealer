package com.strongdealer.mobile.exception;

import com.strongdealer.mobile.model.ApiResponse;
import com.strongdealer.mobile.model.HttpResponseMessage;
import com.strongdealer.mobile.model.HttpStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleException(UserNotFoundException e) {
        return new ResponseEntity<>(ApiResponse.response(
                HttpStatusCode.NOT_FOUND,
                e.getId()+ " No Such User in DB"), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleException(CarNotFoundException e) {

        return new ResponseEntity<>(ApiResponse.response(
                HttpStatusCode.NOT_FOUND,
                e.getId()+" No Such Car in DB"), HttpStatus.BAD_REQUEST
        );
    }
}
