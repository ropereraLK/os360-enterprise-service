package com.os360.enterprise.exception;

public abstract class EntityOperationException extends RuntimeException{
    public EntityOperationException(String message) {
        super(message);
    }

    public EntityOperationException(String message, Throwable cause) {
        super(message, cause);
    }

}
