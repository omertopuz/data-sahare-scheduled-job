package com.batch.scheduled.model.portalservice;

public class InformStateResponse {
    private boolean result;
    private String message;

    public InformStateResponse(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public InformStateResponse() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "InformStateResponse{" +
                "result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}