package com.infy.student_crud_app_1.utils;

import com.infy.student_crud_app_1.exception.StudentException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice//it will handle all the exceptions globally
public class ExceptionControllerAdvice {
    @Autowired
    Environment env;//used to get the properties from the application.properties file

    @ExceptionHandler(Exception.class)//it will handle all the exceptions
    public ResponseEntity<ErrorInfo> handleGlobalException(Exception ex) throws StudentException{
        ErrorInfo er=new ErrorInfo();
        er.setMessage(env.getProperty("Global_Exception"));
        er.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(er,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(StudentException.class)//it will handle user defined exceptions
    public ResponseEntity<ErrorInfo> handleGlobalException(StudentException ex) throws StudentException{
        ErrorInfo er=new ErrorInfo();
        er.setMessage(env.getProperty(ex.getMessage()));
        er.setErrorCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
    }

    //MethodArgumentNotValidException->JSON LO INVALID DATA WILL CATCH HERE
    //ConstraintViolationException->URI Exception will caught here
    @ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> methodAndConst(Exception ex){
        ErrorInfo er=new ErrorInfo();
        String methodMsg;
        if(ex instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException msg= (MethodArgumentNotValidException) ex;
            methodMsg=msg.getBindingResult().getAllErrors().stream().map(sita->sita.getDefaultMessage()).collect(Collectors.joining(", "));
        }else{
            ConstraintViolationException msg=(ConstraintViolationException) ex;
            methodMsg=msg.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
        }
        er.setMessage(methodMsg);
        er.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(er,HttpStatus.BAD_REQUEST);
    }

}
