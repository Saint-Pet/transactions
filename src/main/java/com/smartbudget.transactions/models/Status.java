package com.transaction.models;

import jakarta.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    private Integer status_id;
    private String status_name;
    private String description;

    // Getters and Setters
    public Integer getStatus_id() {
        return status_id;
    }
    public String getStatus_name(){
        return status_name;
    }
    public String getDescription() {
        return description;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }
}
