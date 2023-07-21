package com.ironhack.homework2;

import java.util.concurrent.atomic.AtomicInteger;

public class Student {

    //CONSTANTS

    private String studentId;
    private String name;
    private String address;
    private String email;
    private static final AtomicInteger studentIdCounter = new AtomicInteger(1000);
//  Course course;
//  ADD OPTIONAL ATTRIBUTES


//  Parametrized constructor
    public Student(String name, String address, String email) {
        setName(name);
        setAddress(address);
        setEmail(email);
        setStudentId();
    }


//  Getters


    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

//  Setters


    public void setStudentId() {
        this.studentId = String.valueOf(studentIdCounter.getAndIncrement());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
