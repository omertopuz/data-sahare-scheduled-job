package com.batch.scheduled.model.portalservice;

public class DeleteFileMetaResponse {
    private boolean result;
    private String message;

    public DeleteFileMetaResponse() {
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
}
