package org.motivators.wavy_project.global.exception;

public enum ErrorCode {

    AUTHENTICATION_FAILED(123, "authentication failed."),
    DUPLICATE_USERNAME(234, "username already exists."),
    INVALID_JWT(456, "jwt validation failed.");

    private int code;
    private String message;
    
    private ErrorCode(int code, String message) {
         this.code = code; 
         this.message = message; 
    } 
    public int getCode() { 
        return code; 
    } 
    public String getMessage() {
        return message; 
    }
}