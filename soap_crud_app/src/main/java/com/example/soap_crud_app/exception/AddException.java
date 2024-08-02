package com.example.soap_crud_app.exception;

public class AddException extends RuntimeException {
    private final int errorCode;
    public AddException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AddException(int errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public AddException(int errorCode,String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AddException(int errorCode,Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
}
