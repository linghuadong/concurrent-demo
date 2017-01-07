package com.linghd.controller.support;

import com.linghd.service.exception.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Ling on 2017/1/5.
 */

@RestControllerAdvice(annotations = {RestController.class})
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ServiceException.class})
    public String ServiceError(final ServiceException e){
        return e.getMessage();
    }

}
