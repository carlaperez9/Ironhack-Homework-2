package com.ironhack.homework2;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    // LookUpStudent Tests
    @Test
    void testLookUpStudentWithValidId() {
        Map<String, Student> studentMap = new HashMap<>();
        studentMap.put("1000", new Student("Lamar Odom", "Lamar's Street 304", "lamar@odom.com"));
        studentMap.put("1001", new Student("Samira Legend", "Legends 4040 House", "samira@legends.com"));

        String validId = "1001";
        Menu.lookUpStudent(validId, studentMap);
    }

    @Test
    void testLookUpStudentWithInvalidId(){
        Map<String, Student> studentMap = new HashMap<>();
        studentMap.put("1000", new Student("Lamar Odom", "Lamar's Street 304", "lamar@odom.com"));

        String invalidId = "999";
        Menu.lookUpStudent(invalidId, studentMap);
    }

    @Test
    void testLookUpTeacherWithValidId() {
        Map<String, Teacher> teacherMap = new HashMap<>();
        Teacher teacher = new Teacher("Orlando Medina", 200000);
        teacherMap.put(teacher.getTeacherId(), teacher);

        String validId = teacher.getTeacherId();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Menu.lookUpTeacher(validId, teacherMap);

        String expectedOutput = "Teacher Info: \nID: 1000\nName: Orlando Medina\nSalary: 200000.0\n-----------------------------------\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testLookUpTeacherWithInvalidId(){
        Map<String, Teacher> teacherMap = new HashMap<>();
        Teacher teacher = new Teacher("Orlando Medina", 200000);
        teacherMap.put(teacher.getTeacherId(), teacher);

        String validId = "1";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Menu.lookUpTeacher(validId, teacherMap);

        String expectedOutput = "Teacher with ID: 1 not found.\n";
        assertEquals(expectedOutput, outputStream.toString());
    }


}