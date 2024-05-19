package com.transaction.model;

import jakarta.persistence.*;

@Entity
@Table(name = "statuses")
public class Status {

    @Id
    @Column(name = "status_id")
    private Integer id;

    @Column(name = "status_name")
    private String statusName
            ;
    private String description;

    public Integer getId() {
        return id;
    }
    public String getStatusName(){
        return statusName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatusName(String status_name) {
        this.statusName = status_name;
    }
}
