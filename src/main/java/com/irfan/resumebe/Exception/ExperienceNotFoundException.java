package com.irfan.resumebe.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ExperienceNotFoundException extends RuntimeException  {

    public ExperienceNotFoundException(String message) {
        super(message);
    }

    public ExperienceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
