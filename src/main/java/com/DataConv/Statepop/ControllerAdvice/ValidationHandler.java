package com.DataConv.Statepop.ControllerAdvice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler {
    private static Logger logger= LogManager.getLogger(ValidationHandler.class);


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> Validation(MethodArgumentNotValidException ex){
        Map<String,String> errorMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        logger.error("Validation exception has been occurred while saving the data please check below for detailed exception");
        return errorMap;
    }

    @ExceptionHandler(InvalidDistNameEx.class)
    public ResponseEntity<String> InvalidDistName(InvalidDistNameEx e){
        logger.error("District name must be a String.");
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
