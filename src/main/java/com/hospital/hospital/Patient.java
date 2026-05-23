package com.hospital.hospital;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;
    private int age;
    private String blood;
    private String type;
    private int room;
    private String status;
    private double bill;

    public Patient() {
    }

    public Patient(
            String name,
            int age,
            String blood,
            String type,
            int room,
            String status,
            double bill
    ) {
        this.name = name;
        this.age = age;
        this.blood = blood;
        this.type = type;
        this.room = room;
        this.status = status;
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBlood() {
        return blood;
    }

    public String getType() {
        return type;
    }

    public int getRoom() {
        return room;
    }

    public String getStatus() {
        return status;
    }

    public double getBill() {
        return bill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }
}