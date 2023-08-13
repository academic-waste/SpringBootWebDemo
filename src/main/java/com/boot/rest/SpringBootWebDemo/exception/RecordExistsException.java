package com.boot.rest.SpringBootWebDemo.exception;

public class RecordExistsException extends Exception{
    public RecordExistsException(String message) {
        super(message);
    }
}
