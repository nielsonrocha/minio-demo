package com.example.demo;

public class MinioException extends RuntimeException {

    public MinioException(Throwable cause) {
        super(cause);
    }

    public MinioException(String message) {
        super(message);
    }
}
