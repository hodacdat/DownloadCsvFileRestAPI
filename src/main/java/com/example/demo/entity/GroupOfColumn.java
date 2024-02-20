package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table
public class GroupOfColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long curId;
    private String nameOfColumn;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCurId() {
        return curId;
    }

    public void setCurId(long curId) {
        this.curId = curId;
    }

    public String getNameOfColumn() {
        return nameOfColumn;
    }

    public void setNameOfColumn(String nameOfColumn) {
        this.nameOfColumn = nameOfColumn;
    }
}
