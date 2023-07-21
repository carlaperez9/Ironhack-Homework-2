package com.ironhack.homework2;

public class Student {

    //CONSTANTS

    private String studentId;
    private String name;
    private String address;
    private String email;
    int idNumber = 1000;
//  Course course;
//  ADD OPTIONAL ATTRIBUTES


//  Parametrized constructor
    public Student(String name, String address, String email) {
        setName(name);
        setAddress(address);
        setEmail(email);
    }

//  Constructor containing all variables - ADD Course course

    public Student(String studentId, String name, String address, String email) {
        setStudentId(studentId);
        setName(name);
        setAddress(address);
        setEmail(email);
//        this.course = null;
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


    public void setStudentId(String studentId) {
        idNumber++;
        studentId = Integer.toString(idNumber);
        this.studentId = studentId;
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
