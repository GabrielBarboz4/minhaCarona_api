package com.minhaCarona.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarpoolNotFoundException extends RuntimeException {
    public CarpoolNotFoundException(String message) {
        super(message);
    }
}
