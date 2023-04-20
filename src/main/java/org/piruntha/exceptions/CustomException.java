package org.piruntha.exceptions;

public class CustomException extends RuntimeException{

    private int statusCode;

    public CustomException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CustomException(String message ) {
        super(message);
    }

    public CustomException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public CustomException(Throwable cause, int statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
