package com.csi.ems.model;

public class ApiResponse {

        private int status;
        private String message;
        private Object anyObject;

        public ApiResponse(int status, String message, Object anyObject){
            this.status = status;
            this.message = message;
            this.anyObject = anyObject;
        }

        public ApiResponse(int status, String message){
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }
        public String getMessage(){return message;}
        public Object getResult(){return anyObject;}


}
