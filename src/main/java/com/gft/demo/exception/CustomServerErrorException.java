package com.gft.demo.exception;

public class CustomServerErrorException extends RuntimeException {
    public CustomServerErrorException(String message) {
        super(message);
    }
}
