package com.batch.scheduled.model.dbservice;

public class InformEvaluationForms {
    private int applicationId;
    private int versionId;
    private int detailId;
    private int entityId;
    private String applicantTaxNumber;
    private Integer informedEvaluationFormIdInAdvance;
    private int willBeInformedEvaluationFormId;

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

    public Integer getInformedEvaluationFormIdInAdvance() {
        return informedEvaluationFormIdInAdvance;
    }

    public void setInformedEvaluationFormIdInAdvance(Integer informedEvaluationFormIdInAdvance) {
        this.informedEvaluationFormIdInAdvance = informedEvaluationFormIdInAdvance;
    }

    public int getWillBeInformedEvaluationFormId() {
        return willBeInformedEvaluationFormId;
    }

    public void setWillBeInformedEvaluationFormId(int willBeInformedEvaluationFormId) {
        this.willBeInformedEvaluationFormId = willBeInformedEvaluationFormId;
    }
}
