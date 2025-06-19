package com.example.orders.exepctions;

import com.common.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorMessageDTO> handleDomainException(
            HttpServletRequest request,
            DomainException ex
    ) {
        ErrorMessageDTO error = new ErrorMessageDTO(
                ex.getCode(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}