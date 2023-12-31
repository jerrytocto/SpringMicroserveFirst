package com.microservice.student.exeption;

public class DatabaseOperationException extends RuntimeException{

    private String message ;
    private String operation;

    public DatabaseOperationException (String operation, String message, Throwable causa){
        super(String.format("Error durante la operación '%s': %s",operation, causa), causa);
    }
}
