package com.project.tinyurl.exception;

public class TinyUrlException extends RuntimeException{

    public TinyUrlException(String message){
        super(message);
    }
    public TinyUrlException(String message, Throwable err){
        super(message , err);
    }
}
