package com.infy.student_crud_app_1.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorInfo {
    private String message;
    private Integer errorCode;

}
