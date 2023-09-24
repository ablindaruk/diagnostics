package com.medical.hospital.diagnostics.exceptions;

public class AuthTokenException extends RuntimeException {
    public AuthTokenException(String message) {
        super(message);
    }
}
