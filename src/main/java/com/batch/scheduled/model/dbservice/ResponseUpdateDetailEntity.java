package com.batch.scheduled.model.dbservice;

public class ResponseUpdateDetailEntity {
    private boolean isSucceded;
    private String message;

    public boolean isSucceded() {
        return isSucceded;
    }

    public void setSucceded(boolean succeded) {
        isSucceded = succeded;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseUpdateDetailEntity{" +
                "isSucceded=" + isSucceded +
                ", message='" + message + '\'' +
                '}';
    }
}
