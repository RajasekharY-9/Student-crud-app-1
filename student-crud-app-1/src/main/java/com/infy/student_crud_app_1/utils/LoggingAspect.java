package com.infy.student_crud_app_1.utils;

import jakarta.persistence.Column;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component//Bean class ga declare
@Aspect
public class LoggingAspect {

    static final Logger logger= LogManager.getLogger(LoggingAspect.class);
    @AfterThrowing(pointcut ="execution(* com.infy.student_crud_app_1.service.*Impl.*(..))" ,throwing = "exception")
    //It will print the exception after errors came(It will use when exception come
    public void printErrorsinConsole(Exception exception) throws  Exception{
        logger.error(exception.getMessage(),exception);
    }



}
