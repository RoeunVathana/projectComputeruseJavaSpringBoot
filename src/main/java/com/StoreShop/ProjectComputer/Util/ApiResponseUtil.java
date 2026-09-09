package com.StoreShop.ProjectComputer.Util;

import com.StoreShop.ProjectComputer.Response.ErrorResponse;
import com.StoreShop.ProjectComputer.Response.PaginationResponse;
import com.StoreShop.ProjectComputer.Response.SuccessResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;

public class ApiResponseUtil{
    public static <T> SuccessResponse<T> success(HttpStatus status) {
        return SuccessResponse.<T>builder()
                .status(status.getReasonPhrase())
                .code(status.value())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    // Success response with payload
    public static <T> SuccessResponse<T> success(HttpStatus status, T payload) {
        return SuccessResponse.<T>builder()
                .payload(payload)
                .status(status.getReasonPhrase())
                .code(status.value())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    // Paginated response
    public static <T> PaginationResponse<List<T>> pagination(HttpStatus status, Page<T> page) {
        return PaginationResponse.<List<T>>builder()
                .payload(page.getContent())
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements((int) page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .status(status.getReasonPhrase())
                .code(status.value())
                .localDateTime(LocalDateTime.now())
                .build();
    }

    // Error response with status and message
    public static ErrorResponse error(HttpStatus status, String message) {
        return ErrorResponse.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Error response with status, message and path
    public static ErrorResponse error(HttpStatus status, String message, String path) {
        return ErrorResponse.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();
    }

    // Error response with status, message, details, and path
    public static ErrorResponse error(HttpStatus status, String message, String details, String path) {
        return ErrorResponse.builder()
                .message(message)
                .details(details)
                .status(status.getReasonPhrase())
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();
    }

    // Error response with status, message and validation errors (Map)
    public static ErrorResponse error(HttpStatus status, String message, Map<String, String> validationErrors) {
        return ErrorResponse.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .validationErrors(validationErrors)
                .build();
    }

    // Error response with status, message, validation errors (Map) and path
    public static ErrorResponse error(HttpStatus status, String message, Map<String, String> validationErrors, String path) {
        return ErrorResponse.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .validationErrors(validationErrors)
                .path(path)
                .build();
    }

    // Error response with status, message and list of errors
    public static ErrorResponse error(HttpStatus status, String message, List<String> errors) {
        return ErrorResponse.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
    }

    // Error response with status, message, list of errors and path
    public static ErrorResponse error(HttpStatus status, String message, List<String> errors, String path) {
        return ErrorResponse.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .code(status.value())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .path(path)
                .build();
    }

    // Error response from existing ErrorResponse
    public static ErrorResponse error(ErrorResponse errorPayload) {
        if (errorPayload.getTimestamp() == null) {
            errorPayload.setTimestamp(LocalDateTime.now());
        }
        return errorPayload;
    }
}
