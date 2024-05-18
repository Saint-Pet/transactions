package com.transaction.models;

import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class Type {

    @Id
    private Integer type_id;
    private String type_name;
    private String description;

    // Getters and Setters
    public Integer getType_id() {
        return type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public String getDescription(){
        return description;
    }

    public void setType_id(Integer id) {
        this.type_id = id;
    }

    public void setType_name(String name) {
        this.type_name = name;
    }

    public void setDescription(String description){
        this.description=description;
    }
}
