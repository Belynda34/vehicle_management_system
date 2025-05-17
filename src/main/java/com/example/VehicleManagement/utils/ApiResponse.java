package com.example.VehicleManagement.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    public boolean success ;
    public String message;
    public T data;
//    private PaginationInfo pagination;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }


}
