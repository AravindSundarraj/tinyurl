package com.project.tinyurl.exception;

public class ClientExistException extends RuntimeException{
    public ClientExistException(String message){
        super(message);
    }
    public ClientExistException(String message, Throwable err){
        super(message , err);
    }
}
