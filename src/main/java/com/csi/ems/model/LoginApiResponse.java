package com.csi.ems.model;

public class LoginApiResponse {

    private int status;
    private String message;
    private Object user;

    public LoginApiResponse(int status, String message, Object user){
        this.status = status;
        this.message = message;
        this.user = user;
    }

    public LoginApiResponse(int status, String message){
        this.status = status;
        this.message = message;
    }



    public int getStatus() {
        return status;
    }
    public String getMessage(){return message;}
    public Object getResult(){return user;}
}
