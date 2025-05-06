package com.example.VehicleManagement.auth.exceptions;

public class EmailTakenException extends RuntimeException{
    private String message;

    public EmailTakenException(String message){
        super(message);
        this.message = message != null ? message : "Email taken";
    }
}
