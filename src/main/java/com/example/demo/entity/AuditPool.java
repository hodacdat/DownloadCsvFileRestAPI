package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.IdGeneratorType;

@Entity
@Table
public class AuditPool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private String bioId;

    public AuditPool() {
    }

    public AuditPool(long id, String content, String bioId) {
        this.id = id;
        this.content = content;
        this.bioId = bioId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBioId() {
        return bioId;
    }

    public void setBioId(String bioId) {
        this.bioId = bioId;
    }

    @Override
    public String toString() {
        return "AuditPool{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", bioId='" + bioId + '\'' +
                '}';
    }
}
