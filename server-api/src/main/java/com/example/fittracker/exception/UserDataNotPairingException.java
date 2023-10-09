package com.example.fittracker.exception;

public class UserDataNotPairingException extends RuntimeException {

    public UserDataNotPairingException(String message) {
        super(message);
    }

    public UserDataNotPairingException(String message, Throwable cause) {
        super(message, cause);
    }

}