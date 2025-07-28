package com.example.EmpApp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseDto<T> {

    private String message;
    private T data;
    private Boolean success;


}
