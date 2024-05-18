package com.transaction.models;

import jakarta.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    private String status_name;
    private String description;

    // Getters and Setters
    public Integer getStatusId() {
        return statusId;
    }
    public String getStatus_name(){
        return status_name;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
