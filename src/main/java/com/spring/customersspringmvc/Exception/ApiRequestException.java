package com.spring.customersspringmvc.Exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
