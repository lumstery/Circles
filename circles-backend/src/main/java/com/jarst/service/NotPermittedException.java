package com.jarst.service;

public class NotPermittedException extends RuntimeException {

    NotPermittedException(String message) {
        super(message);
    }

}
