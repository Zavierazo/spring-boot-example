package com.example.exception;

public class AppException extends Exception {
    private static final long serialVersionUID = -1345662399091922016L;

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, boolean isStackUseful) {
        super(message);
    }

    public AppException(String message, Throwable cause, boolean isStackUseful) {
        super(message, cause);
    }

    public AppException(Throwable cause, boolean isStackUseful) {
        super(cause);
    }

}
