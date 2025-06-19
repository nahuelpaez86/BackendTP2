package com.example.practice.execeptions;

import com.common.dto.ErrorMessageDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class ProductExceptions extends RuntimeException{
    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ErrorMessageDTO> productNotExist(HttpServletRequest request, ProductNotExistException ex) {
        ErrorMessageDTO error = new ErrorMessageDTO(404, ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(error, HttpStatus.FAILED_DEPENDENCY);
    }

}
