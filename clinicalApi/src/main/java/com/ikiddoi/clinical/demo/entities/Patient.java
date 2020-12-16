package com.ikiddoi.clinical.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Patient {

    @Id
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
    private List<Clinicaldata> clinicalData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Clinicaldata> getClinicalData() {
        return clinicalData;
    }

    public void setClinicalData(List<Clinicaldata> clinicalData) {
        this.clinicalData = clinicalData;
    }

}
