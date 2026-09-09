package com.StoreShop.ProjectComputer.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse<T> {
    private String message;
    private String details;
    private String status;
    private Integer code;
    private LocalDateTime timestamp;
    private String path;
    private Map<String, String> validationErrors;
    private List<String> errors;
}
