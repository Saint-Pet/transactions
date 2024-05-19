package com.smartbudget.transactions.model;

import jakarta.persistence.*;

@Entity
@Table(name = "types")
public class Type {

    @Id
    @Column(name = "type_id")
    private Integer id;

    @Column(name = "type_name")
    private String typeName;

    private String description;

    public Integer getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getDescription(){
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTypeName(String name) {
        this.typeName = name;
    }

    public void setDescription(String description){
        this.description=description;
    }
}
