package com.StoreShop.ProjectComputer.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessResponse<T> {
    private final String message = "Successfully";
    private String status;
    private Integer code;
    private LocalDateTime localDateTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T payload;
}
