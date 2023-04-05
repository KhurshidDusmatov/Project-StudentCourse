package com.company.controller;

import com.company.exp.AppBadRequestException;
import com.company.exp.PhoneAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler({AppBadRequestException.class, PhoneAlreadyExistsException.class})
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

   /* @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<String> handleException(PhoneAlreadyExistsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }*/
}
