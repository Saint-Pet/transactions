package com.transaction.models;

import jakarta.persistence.*;

@Entity
@Table(name = "banks")
public class Bank {

    @Id
    private Integer bank_id;
    private String name;
    private String bank_code;
    private String address;
    private String phone_number;
    private String website;


    // Getters
    public Integer getBank_id() {
        return bank_id;
    }
    public String getName() {
        return name;
    }
    public String getBank_code(){ return bank_code;}
    public String getAddress(){ return address;}
    public String getPhone_number(){ return phone_number;}
    public String getWebsite(){ return website;}

    public void setId(Integer id) {
        this.bank_id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}

