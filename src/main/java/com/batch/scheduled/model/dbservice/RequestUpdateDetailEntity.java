package com.batch.scheduled.model.dbservice;

import java.util.Map;

public class RequestUpdateDetailEntity {
    private int detailId;
    private Map<String,String> updateParts;

    public RequestUpdateDetailEntity(int detailId, Map<String, String> updateParts) {
        this.detailId = detailId;
        this.updateParts = updateParts;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public Map<String, String> getUpdateParts() {
        return updateParts;
    }

    public void setUpdateParts(Map<String, String> updateParts) {
        this.updateParts = updateParts;
    }
}
