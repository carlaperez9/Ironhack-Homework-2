package com.ironhack.homework2;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Student student1 = new Student("Carla","parada 22", "carla@popular.com");
    }

    public static void commandCenter(){
        String command = scanner.nextLine();

        switch(command){
            case "ENROLL":
                //enrollStudents(student1.getStudentId(), courseId);
                break;
            case "ASSIGN":
                //assignTeacher(teacherId, courseId);
                break;
            case "SHOW COURSES":
                showCourses();
                break;
            case "LOOKUP COURSE":
                //lookUpCourse(courseId);
                break;
            case "SHOW STUDENTS":
                showStudents();
                break;
            case "LOOKUP STUDENT":
                //lookUpStudent(studentId);
                break;
            case "SHOW TEACHERS":
                showTeachers();
                break;
            case "LOOKUP TEACHER":
                //lookUpTeacher(teacherId);
                break;
            case "SHOW PROFIT":
                showProfit();
                break;
        }
    }

    private static void enrollStudents(String studentId, String courseId) {
    }

    private static void assignTeacher(String teacherId, String courseId) {
    }

    private static void showCourses() {
    }

    private static void lookUpCourse(String courseId) {
    }

    private static void showStudents() {
    }

    private static void lookUpStudent(String studentId) {
    }

    private static void showTeachers() {
    }

    private static void lookUpTeacher(String teacherId) {
    }

    private static void showProfit() {
    }

    // create teachers Method
    public static HashMap<String, Teacher> createTeacherMap(int n, Scanner scanner){
        String name;
        double salary;
        HashMap<String,Teacher> teacherMap = new HashMap<String, Teacher>();

        for (int i = 0; i < n; i++) {
            System.out.println("Please enter the teacher name:");
            name = scanner.nextLine();

            if (name == null){
                throw new NullPointerException("Teacher name is null!");
            }
            // TODO: check exceptions
            if (name.isEmpty()){
                throw new UnsupportedOperationException("Teacher name is empty");
            }

            System.out.println("Please enter the salary");
            salary = scanner.nextDouble();

            Teacher teacher = new Teacher(name, salary);
            teacherMap.put(teacher.getTeacherId(), teacher);
        }

        return teacherMap;
    }


}
