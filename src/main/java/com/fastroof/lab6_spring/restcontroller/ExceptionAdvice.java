package com.fastroof.lab6_spring.restcontroller;

import com.fastroof.lab6_spring.pojo.SimpleMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    SimpleMessage roomNotFoundHandler(RoomNotFoundException ex) {
        return new SimpleMessage(ex.getLocalizedMessage());
    }

    @ExceptionHandler(UserNotOwnerException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    SimpleMessage userNotOwnerHandler(UserNotOwnerException ex) {
        return new SimpleMessage(ex.getLocalizedMessage());
    }
}
