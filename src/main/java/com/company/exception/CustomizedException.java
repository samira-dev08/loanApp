package com.company.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@RestControllerAdvice
public class CustomizedException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IdentityException.class)
    public final ErrorDetails handleIdentityError(Exception ex){
        return ErrorDetails.builder()
                .uniqueExceptionId(UUID.randomUUID())
                .message(ex.getMessage())
                .timeStamp(LocalDate.now())
                .build();
    }

    @ExceptionHandler(InitialException.class)
    public final ErrorDetails handleInitialError(Exception ex){
        return ErrorDetails.builder()
                .uniqueExceptionId(UUID.randomUUID())
                .message(ex.getMessage())
                .timeStamp(LocalDate.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public final ErrorDetails handleNotFoundError(Exception ex){
        return ErrorDetails.builder()
                .uniqueExceptionId(UUID.randomUUID())
                .message(ex.getMessage())
                .timeStamp(LocalDate.now())
                .build();
    }
}
