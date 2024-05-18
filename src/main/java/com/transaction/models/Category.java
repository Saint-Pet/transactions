package com.transaction.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private Integer category_id;
    private String category_name;
    private String description;

    // Getters and Setters
    public Integer getCategory_id() {
        return category_id;
    }
    public String getCategory_name() {
        return category_name;
    }
    public String getDescription(){
        return description;
    }

    public void setCategory_id(Integer category_id){
        this.category_id=category_id;
    }
}
