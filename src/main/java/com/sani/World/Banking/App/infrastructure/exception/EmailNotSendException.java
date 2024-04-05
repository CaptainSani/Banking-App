package com.sani.World.Banking.App.infrastructure.exception;

public class EmailNotSendException extends RuntimeException{

    public EmailNotSendException(String message) {
        super(message);
    }
}
