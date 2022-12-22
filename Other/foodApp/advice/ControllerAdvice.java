package com.majorEvaluation.foodApp.advice;

import com.majorEvaluation.foodApp.exception.InvalidUserCredentialException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(InvalidUserCredentialException.class)
    public ResponseEntity<String> exception(InvalidUserCredentialException invalidUserCredentialException) {
        return new ResponseEntity<String>(invalidUserCredentialException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException noSuchElementException) {
        return new ResponseEntity<String>(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String> stringStringMap=new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(errors -> {
            stringStringMap.put(errors.getField(), errors.getDefaultMessage());
        });
        return stringStringMap;
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String ,String > handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException){
        Map<String,String> stringStringMap=new HashMap<>();
        stringStringMap.put("error message" , sqlIntegrityConstraintViolationException.getMessage());
        return stringStringMap;
    }
}
