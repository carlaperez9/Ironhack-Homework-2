package com.ironhack.homework2;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Student student1 = new Student("Carla","parada 22", "carla@popular.com");
        System.out.println("SCHOOL MANAGEMENT SYSTEM");
        System.out.println("Enter school name: ");
        String schoolName = scanner.nextLine();

        System.out.println("Enter amount of teachers to create: ");
        int n = scanner.nextInt();
        Map<String, Teacher> teacherMap = createTeacherMap(n, scanner);

        System.out.println("Enter amount of courses to create: ");
        int numberCourse = scanner.nextInt();
        Map<String, Course> courseMap = createCourseMap(numberCourse, scanner);

        System.out.println("Enter amount of students to create: ");
        int numberStudent = scanner.nextInt();
        Map<String, Student> studentMap = createStudentMap(numberStudent, scanner);

        //command center
        //while
        commandCenter(studentMap, teacherMap, courseMap);


    }

    public static void commandCenter(Map<String, Student> studentMap, Map<String, Teacher> teacherMap, Map<String, Course> courseMap){

        System.out.println("\nCommand Center: ");
        System.out.println("--> ENROLL [STUDENT_ID] [COURSE_ID]"); //FABIOLA ENROLL 1 2
        System.out.println("--> ASSIGN [TEACHER_ID] [COURSE_ID]"); //FABIOLA
        System.out.println("--> SHOW COURSES"); //CARLA
        System.out.println("--> LOOKUP COURSE [COURSE_ID]"); //CARLA
        System.out.println("--> SHOW STUDENTS"); //MERIANNI
        System.out.println("--> LOOKUP STUDENT [STUDENT_ID]"); //MERIANNI
        System.out.println("--> SHOW TEACHERS"); //MERIANNI
        System.out.println("--> LOOKUP TEACHER [TEACHER_ID]"); //ORLANDO
        System.out.println("--> SHOW PROFIT"); //ORLANDO

        String answer = scanner.nextLine();
        String[] commandParts = answer.split("\\s+", 2);
        String command = commandParts[0];

        switch (command) {
            case "ENROLL":
                System.out.println("Command: ENROLL");
                break;
            case "ASSIGN":
                System.out.println("Command: ASSIGN");
                break;
            case "SHOW":
                String show = commandParts[1];
                switch (show) {
                    case "COURSES":
                        System.out.println("Command: SHOW COURSES");
                        break;
                    case "STUDENTS":
                        showStudents(studentMap);
                        break;
                    case "TEACHERS":
                        showTeachers(teacherMap);
                        break;
                    case "PROFIT":
                        System.out.println("Command: SHOW PROFIT");
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }
                break;
            case "LOOKUP":
                String[] lookupArgs = commandParts[1].split("\\s+", 2);
                String lookup = lookupArgs[0];
                System.out.println(lookupArgs[1]);
                switch (lookup) {
                    case "COURSE":
                        System.out.println("Command: LOOKUP COURSE");
                        break;
                    case "STUDENT":
                        lookUpStudent(lookupArgs[1], studentMap);
                        break;
                    case "TEACHER":
                        System.out.println("Command: LOOKUP TEACHER");
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }
                break;
            default:
                System.out.println("Invalid Command");
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

    private static void showStudents(Map<String, Student> studentMap) {
        // show a list of all students
        System.out.println("List of Students: ");
        for (Student student : studentMap.values()){
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Email: " + student.getEmail());
            System.out.println("-----------------------------------");
        }
    }

    private static void lookUpStudent(String studentId, Map<String, Student> studentMap) {
        // look up a specific student by their ID
        Student student = studentMap.get(studentId);
        if (student != null){
            System.out.println("Student Info: ");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Email: " + student.getEmail());
        } else {
            System.out.println("Student with ID: " + studentId + " not found.");
        }
    }

    private static void showTeachers(Map<String, Teacher> teacherMap) {
        // show a list of all teachers
        System.out.println("List of Teachers: ");
        for (Teacher teacher : teacherMap.values()){
            System.out.println("ID: " + teacher.getTeacherId());
            System.out.println("Name: " + teacher.getName());
            System.out.println("Salary: " + teacher.getSalary());
            System.out.println("-----------------------------------");
        }
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
        scanner.nextLine();
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
            scanner.nextLine();
        }
        System.out.println("Teachers were created successfully");
        return teacherMap;
    }

    public static HashMap<String, Student> createStudentMap(int n, Scanner scanner){
        String name, address, email;
        HashMap<String,Student> studentMap = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Please enter the student name:");
            name = scanner.nextLine();
            if (name == null){
                throw new NullPointerException("Student name is null!");
            }
            // TODO: check exceptions
            if (name.isEmpty()){
                throw new UnsupportedOperationException("Student name is empty");
            }

            System.out.println("Please enter the student address: ");
            address = scanner.nextLine();

            if (address == null){
                throw new NullPointerException("Student address is null!");
            }
            // TODO: check exceptions
            if (address.isEmpty()){
                throw new UnsupportedOperationException("Student address is empty");
            }

            System.out.println("Please enter the student email");
            email = scanner.nextLine();
            if (email == null){
                throw new NullPointerException("Student email is null!");
            }
            // TODO: check exceptions
            if (email.isEmpty()){
                throw new UnsupportedOperationException("Student email is empty");
            }

            Student student = new Student(name, address, email);
            studentMap.put(student.getStudentId(), student);
        }
        System.out.println("Students were created successfully");
        return studentMap;
    }

    public static HashMap<String, Course> createCourseMap(int n, Scanner scanner){
        String name;
        double price;
        HashMap<String,Course> courseMap = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Please enter the course name:");
            name = scanner.nextLine();
            if (name == null){
                throw new NullPointerException("Student name is null!");
            }
            // TODO: check exceptions
            if (name.isEmpty()){
                throw new UnsupportedOperationException("Student name is empty");
            }
            System.out.println("Please enter the course's price: ");
            price = scanner.nextDouble();

            Course course = new Course(name, price);
            courseMap.put(course.getCourseId(), course);
        }
        System.out.println("Courses were created successfully");
        return courseMap;
    }

}
