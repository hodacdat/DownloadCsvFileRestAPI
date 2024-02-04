package com.example.demo.dto;

import com.example.demo.entity.AuditPool;

public class AuditPoolDTO {

    private AuditPool auditPool;

    private long configID;

    public AuditPoolDTO() {
    }

    public AuditPoolDTO(AuditPool auditPool) {
        this.auditPool = auditPool;
        this.configID = 1;
    }

    public AuditPool getAuditPool() {
        return auditPool;
    }

    public void setAuditPool(AuditPool auditPool) {
        this.auditPool = auditPool;
    }

    public long getConfigID() {
        return configID;
    }

    public void setConfigID(long configID) {
        this.configID = configID;
    }

    @Override
    public String toString() {
        return "AuditPoolDTO{" +
                "auditPool=" + auditPool +
                ", configID=" + configID +
                '}';
    }
}
