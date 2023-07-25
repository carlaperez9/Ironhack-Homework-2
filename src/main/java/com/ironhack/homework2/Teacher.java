package com.ironhack.homework2;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Teacher {
    // Testing out to implement in the auto-generated ID
    private static final AtomicInteger idCounter = new AtomicInteger(1000);
    // properties
    private String teacherId;
    private String name;
    private double salary;

    // constructor
    public Teacher(String name, double salary) {
        setTeacherId();
        setName(name);
        setSalary(salary);
    }

    // setters and getters
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId() {
        this.teacherId = String.valueOf(idCounter.getAndIncrement());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
