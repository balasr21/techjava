package com.techjava.stockavailiblity.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO implements  java.io.Serializable   {

    private static final long serialVersionUID = 6412892474640831473L;

    private String message;

    private Status status;

    private HttpStatus statusCode;

    public enum Status {
        SUCCESS, INFO, WARNING, ERROR, FIELDERROR, LOCKED, NOTFOUND, INPROGRESS
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
