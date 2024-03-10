package com.example.demo.dto;

import com.example.demo.entity.AuditPool;

import java.util.HashMap;
import java.util.Map;

public class AuditPoolDTO {

    private AuditPool auditPool;

    private long configID;

    private Map<String, Object> dynamicColumns;

    public AuditPoolDTO() {
        dynamicColumns = new HashMap<>();
    }

    public AuditPoolDTO(AuditPool auditPool) {
        this.auditPool = auditPool;
        this.configID = 1;
        dynamicColumns = new HashMap<>();
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

    //for dynamic
    public Object getDynamicColumn(String columnName) {
        return dynamicColumns.get(columnName);
    }

    public void setDynamicColumn(String columnName, Object columnValue) {
        dynamicColumns.put(columnName, columnValue);
    }

    public Map<String, Object> getDynamicColumns() {
        return dynamicColumns;
    }

    public void setDynamicColumns(Map<String, Object> dynamicColumns) {
        this.dynamicColumns = dynamicColumns;
    }

    @Override
    public String toString() {
        return "AuditPoolDTO{" +
                "auditPool=" + auditPool +
                ", configID=" + configID +
                ", dynamicColumns=" + dynamicColumns +
                '}';
    }
}
