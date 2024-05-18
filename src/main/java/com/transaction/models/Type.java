package com.transaction.models;

import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer typeId;
    private String type_name;
    private String description;

    // Getters and Setters
    public Integer getTypeId() {
        return typeId;
    }
    public String getType_name() {
        return type_name;
    }
    public String getDescription(){
        return description;
    }

    public void setType_name(String name) {
        this.type_name = name;
    }
    public void setDescription(String description){
        this.description=description;
    }
}
