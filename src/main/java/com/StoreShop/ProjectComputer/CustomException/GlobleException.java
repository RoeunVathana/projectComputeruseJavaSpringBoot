package com.StoreShop.ProjectComputer.CustomException;

import com.StoreShop.ProjectComputer.Response.ErrorResponse;
import com.StoreShop.ProjectComputer.Util.ApiResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobleException {

    @ExceptionHandler(Exception.CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(
            Exception.CustomException exception,
            HttpServletRequest request
    ) {
        ErrorResponse response = exception.getErrorResponse();
        response.setPath(request.getRequestURI());
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse response = ApiResponseUtil.error(
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                errors,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(response);
    }
}
