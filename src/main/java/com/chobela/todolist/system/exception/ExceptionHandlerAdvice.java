package com.chobela.todolist.system.exception;

import com.chobela.todolist.Item.ItemNotFoundException;
import com.chobela.todolist.system.Result;
import com.chobela.todolist.system.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleItemNotFoundException(ItemNotFoundException ex){
        return new Result(false, StatusCode.NOT_FOUND, ex.getMessage(), null);
    }
}
