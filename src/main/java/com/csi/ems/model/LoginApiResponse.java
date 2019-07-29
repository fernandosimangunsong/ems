package com.csi.ems.model;

public class LoginApiResponse {

    private int status;
    private String message;
    private Object result;

    public LoginApiResponse(int status, String message, Object result){
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }
}
