package com.DataConv.Statepop.ControllerAdvice;

public class InvalidDistNameEx extends RuntimeException{
    public InvalidDistNameEx(String message){
        super(message);
    }
}
