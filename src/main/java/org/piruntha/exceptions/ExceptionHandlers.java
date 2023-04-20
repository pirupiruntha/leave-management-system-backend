package org.piruntha.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity generalStatusCodeException(CustomException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .header("X-Reason", "user-invalid")
                .body( ex.getMessage());
    }
}
