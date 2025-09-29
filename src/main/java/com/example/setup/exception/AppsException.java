package com.example.setup.exception;

public class AppsException extends Exception{

    private String errorMessage;

    public AppsException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public AppsException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
