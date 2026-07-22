package com.StoreShop.ProjectComputer.Util;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private Integer code;
    private LocalDate timestamp;
    private T data;

    public ApiResponse(Boolean success, String message, Integer code, LocalDate timestamp, T Data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
        this.data = Data;
    }

    public ApiResponse() {
        this.timestamp = LocalDate.now();
    }

    //create
    public static <T> ApiResponse<T> create (T Data){
        return new ApiResponse<T>(true, "Success", HttpStatus.CREATED.value(), LocalDate.now(), Data);
    }
    public static <T> ApiResponse<T> create (T Data, String message){
        return new ApiResponse<T>(true, message, HttpStatus.CREATED.value(), LocalDate.now(), Data);
    }

    // update using list, listOne, remove
    public static <T> ApiResponse<T> ok (T Data){
        return new ApiResponse<T>(true, "Success", HttpStatus.OK.value(), LocalDate.now(), Data);
    }
    public static <T> ApiResponse<T> ok (T Data, String message){
        return new ApiResponse<T>(true, message, HttpStatus.OK.value(), LocalDate.now(), Data);
    }

    //using Error
    public static <T> ApiResponse<T> ok (T Data, String message, HttpStatus status){
        return new ApiResponse<T>(true, message, status.value(), LocalDate.now(), Data);
    }

    public static <T> ApiResponse<T> error (T Data, String message, Integer status){
        return new ApiResponse<T>(false, message, status, LocalDate.now(), Data);
    }

}
