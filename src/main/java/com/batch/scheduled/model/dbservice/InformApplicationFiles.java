package com.batch.scheduled.model.dbservice;

import java.util.Date;

public class InformApplicationFiles {
    private int applicationId;
    private int versionId;
    private int detailId;
    private int entityId;
    private String applicantTaxNumber;
    private Integer informedFileIdInAdvance;
    private Long willBeInformedFileId;
    private Date fileCreationDate;
    private String localFileName;

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getApplicantTaxNumber() {
        return applicantTaxNumber;
    }

    public void setApplicantTaxNumber(String applicantTaxNumber) {
        this.applicantTaxNumber = applicantTaxNumber;
    }

    public Integer getInformedFileIdInAdvance() {
        return informedFileIdInAdvance;
    }

    public void setInformedFileIdInAdvance(Integer informedFileIdInAdvance) {
        this.informedFileIdInAdvance = informedFileIdInAdvance;
    }

    public Long getWillBeInformedFileId() {
        return willBeInformedFileId;
    }

    public void setWillBeInformedFileId(Long willBeInformedFileId) {
        this.willBeInformedFileId = willBeInformedFileId;
    }

    public Date getFileCreationDate() {
        return fileCreationDate;
    }

    public void setFileCreationDate(Date fileCreationDate) {
        this.fileCreationDate = fileCreationDate;
    }

    public String getLocalFileName() {
        return localFileName;
    }

    public void setLocalFileName(String localFileName) {
        this.localFileName = localFileName;
    }
}
