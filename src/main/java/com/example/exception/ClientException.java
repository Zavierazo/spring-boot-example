package com.example.exception;

public class ClientException extends Exception {
    private static final long serialVersionUID = -1345662399091922016L;

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(String message, boolean isStackUseful) {
        super(message);
    }

    public ClientException(String message, Throwable cause, boolean isStackUseful) {
        super(message, cause);
    }

    public ClientException(Throwable cause, boolean isStackUseful) {
        super(cause);
    }

}
