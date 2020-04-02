package com.batch.scheduled.model.fileserver;

public class ResponseFileServer {
    private byte[] blobData;
    protected String fileName;

    public ResponseFileServer(byte[] blobData, String fileName) {
        this.blobData = blobData;
        this.fileName = fileName;
    }

    public byte[] getBlobData() {
        return blobData;
    }

    public void setBlobData(byte[] blobData) {
        this.blobData = blobData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ResponseFileServer{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
