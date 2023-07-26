package com.ironhack.homework2;

import org.junit.jupiter.api.Test;

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


}