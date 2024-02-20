package com.example.demo.dto;

import com.example.demo.entity.AuditPool;

import java.util.HashMap;
import java.util.Map;

public class MyResponseDTO {
    // Static columns
    private Long id;
    private String name;

    private AuditPool auditPool;

    public MyResponseDTO( AuditPool auditPool) {
        this.auditPool = auditPool;
        dynamicColumns = new HashMap<>();

    }

    // Dynamic columns
    private Map<String, Object> dynamicColumns;

    public MyResponseDTO() {
        dynamicColumns = new HashMap<>();
    }

    // Getters and Setters for static columns

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuditPool getAuditPool() {
        return auditPool;
    }

    public void setAuditPool(AuditPool auditPool) {
        this.auditPool = auditPool;
    }

    // Getters and Setters for dynamic columns

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
}