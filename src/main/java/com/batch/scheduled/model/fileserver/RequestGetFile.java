package com.batch.scheduled.model.fileserver;

public class RequestGetFile {
    private long fileId;
    private String serverType;

    public RequestGetFile(long fileId, String serverType) {
        this.fileId = fileId;
        this.serverType = serverType;
    }

    public RequestGetFile(long fileId) {
        this.fileId = fileId;
        this.serverType="KBS";
    }

    public RequestGetFile() {
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    @Override
    public String toString() {
        return "RequestGetFile{" +
                "fileId=" + fileId +
                ", serverType='" + serverType + '\'' +
                '}';
    }
}
