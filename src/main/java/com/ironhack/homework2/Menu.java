package com.ironhack.homework2;

import com.ironhack.homework2.Course;
import com.ironhack.homework2.Teacher;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Teacher> teacherMap;
    private static Map<String, Student> studentMap;
    private static Map<String, Course> courseMap;
    private static boolean startMenu;
    private static boolean exitMenu;
  
    public static void main(String[] args) throws InterruptedException {

        while(startMenu == false) {
           
            System.out.println("SCHOOL MANAGEMENT SYSTEM");
            System.out.println("Enter school name: ");
            String schoolName = scanner.nextLine();

            System.out.println("Enter amount of teachers to create: ");
            int numberTeacher = scanner.nextInt();
            teacherMap = createTeacherMap(numberTeacher);
          
            System.out.println("Enter amount of courses to create: ");
            int numberCourse = scanner.nextInt();
            courseMap = createCourseMap(numberCourse);

            System.out.println("Enter amount of students to create: ");
            int numberStudent = scanner.nextInt();
            studentMap = createStudentMap(numberStudent);

            //command center
            while (exitMenu == false) {
                commandCenter(studentMap, teacherMap, courseMap);
            }
        }
    }

    public static void commandCenter(Map<String, Student> studentMap, Map<String, Teacher> teacherMap, Map<String, Course> courseMap) throws InterruptedException {

        System.out.println("\nCommand Center: ");
        System.out.println("--> ENROLL [STUDENT_ID] [COURSE_ID]");
        System.out.println("--> ASSIGN [TEACHER_ID] [COURSE_ID]");
        System.out.println("--> SHOW COURSES");
        System.out.println("--> LOOKUP COURSE [COURSE_ID]");
        System.out.println("--> SHOW STUDENTS");
        System.out.println("--> LOOKUP STUDENT [STUDENT_ID]");
        System.out.println("--> SHOW TEACHERS");
        System.out.println("--> LOOKUP TEACHER [TEACHER_ID]");
        System.out.println("--> SHOW PROFIT");
        System.out.println("--> SETUP NEW SCHOOL");
        System.out.println("--> EXIT");

        // input validation for command
        String answer = scanner.nextLine();
        String[] commandParts = answer.split(" ");
        String command = commandParts[0];

        switch (command) {
            case "ENROLL":
                if (commandParts.length != 3){
                    System.err.println("Invalid ENROLL command. Valid command: ENROLL [STUDENT_ID] [COURSE_ID]");
                    break;
                }
                enrollStudents(commandParts[1], commandParts[2]);
                break;
            case "ASSIGN":
                if (commandParts.length != 3){
                    System.err.println("Invalid ASSIGN command. Valid command: ASSIGN [TEACHER_ID] [COURSE_ID]");
                    break;
                }
                assignTeacher(commandParts[1], commandParts[2]);
                break;
            case "SHOW":
                if (commandParts.length != 2){
                    System.err.println("Invalid SHOW command. Valid command: SHOW COURSES/STUDENTS/TEACHERS");
                    break;
                }
                String show = commandParts[1];
                switch (show) {
                    case "COURSES":
                        System.out.println("Command: SHOW COURSES");
                        try {
                            showCourses(courseMap);
                        }catch(InterruptedException e){
                            System.out.println("Interruption occurred.");
                        }
                        break;
                    case "STUDENTS":
                        showStudents(studentMap);
                        break;
                    case "TEACHERS":
                        showTeachers(teacherMap);
                        break;
                    case "PROFIT":
                        showProfit(teacherMap, courseMap);
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }
                break;
            case "LOOKUP":
                if (commandParts.length != 3){
                    System.err.println("Invalid LOOKUP command. Valid command: LOOKUP COURSE [COURSE_ID], LOOKUP TEACHER [TEACHER_ID], LOOKUP STUDENT [STUDENT_ID]");
                    break;
                }
                switch (commandParts[1]) {
                    case "COURSE":
                        System.out.println(commandParts[0] + commandParts[1] + commandParts[2]);
                        System.out.println("Command: LOOKUP COURSE");
                        lookUpCourse(commandParts[2], courseMap);
                        break;
                    case "STUDENT":
                        lookUpStudent(commandParts[1], studentMap);
                        break;
                    case "TEACHER":
                        lookUpTeacher(commandParts[1], teacherMap);
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }
                break;

            case "SETUP":
                startMenu = true;
            case "EXIT":
                exitMenu = true;

            default:
                System.out.println("Invalid Command");
        }
    }

    private static void enrollStudents(String studentId, String courseId) {
        Student student = studentMap.get(studentId);
        student.setCourse(courseMap.get(courseId));
        courseMap.get(courseId).setMoney_earned();
        System.out.println("Student "+ student.getName() + " has been enrolled in " + student.getCourse().getName());
    }

    private static void assignTeacher(String teacherId, String courseId) {
        Course course = courseMap.get(courseId);
        course.setTeacher(teacherMap.get(teacherId));
        System.out.println("Teacher "+ course.getTeacher().getName() + " has been assigned to " + course.getName());
    }

    private static void showCourses(Map<String, Course> courseMap) throws InterruptedException {
        // Carla
        System.out.println("Course List:\n");
        for (Course course : courseMap.values()){
            System.out.println("Course: " + course.getName());
            System.out.println("Course Id: " + course.getCourseId());
            System.out.println("Course Price: " + course.getPrice());
            System.out.println("Profit from this course: " + "$" + course.getMoney_earned());
            System.out.println("-----------------------------------");
            Thread.sleep(1500);
        }

    }

    private static void lookUpCourse(String courseId, Map<String, Course> courseMap) {
        // throws an exception if the courseId is null
        if(courseId == null || courseId.isEmpty()){
            throw new IllegalArgumentException("Course ID cannot be null or empty.");
        }

        System.out.println("Course Details: \n");
        System.out.println("Course Name: " + courseMap.get(courseId).getName());
        System.out.println("Course ID: " + courseMap.get(courseId).getCourseId());
        System.out.println("Cost: " +  "$" + courseMap.get(courseId).getPrice());
        System.out.println("Profit from this course: " + "$" + courseMap.get(courseId).getMoney_earned());
        System.out.println("-----------------------------------");

    }

    public static void showStudents(Map<String, Student> studentMap) throws InterruptedException {

        // if student map is null, throws a NullPointerException
        if (studentMap == null){
            throw new NullPointerException("Student map cannot be null");
        }

        // show a list of all students and it delays the print out by 1.5secs
        System.out.println("List of Students: ");
        for (Student student : studentMap.values()){
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Address: " + student.getAddress());
            System.out.println("Email: " + student.getEmail());
            System.out.println("-----------------------------------");
            Thread.sleep(1500);
        }
    }

    public static void lookUpStudent(String studentId, Map<String, Student> studentMap) {
        // throws an exception if the studentId is null
        if(studentId == null || studentId.isEmpty()){
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }

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

    public static void showTeachers(Map<String, Teacher> teacherMap) throws InterruptedException {
        // if teacherMap is null it will throw a NullPointerException
        if (teacherMap == null){
            throw new NullPointerException("Teacher map cannot be null");
        }

        // show a list of all teachers
        System.out.println("List of Teachers: ");
        for (Teacher teacher : teacherMap.values()){
            System.out.println("ID: " + teacher.getTeacherId());
            System.out.println("Name: " + teacher.getName());
            System.out.println("Salary: " + teacher.getSalary());
            System.out.println("-----------------------------------");
            Thread.sleep(1500);
        }
    }

    private static void lookUpTeacher(String teacherId, Map<String, Teacher> teacherMap) {
        // throws an exception if the teacherId is null
        if(teacherId == null || teacherId.isEmpty()){
            throw new IllegalArgumentException("Course ID cannot be null or empty.");
        }

        // get the teacher
        Teacher teacher = teacherMap.get(teacherId);

        // this will print the teacher if it exists
        if (teacher != null){
            System.out.println("Teacher Info: ");
            System.out.println("ID: " + teacher.getTeacherId());
            System.out.println("Name: " + teacher.getName());
            System.out.println("Address: " + teacher.getSalary());
            System.out.println("-----------------------------------");
        } else {
            System.out.println("Teacher with ID: " + teacherId + " not found.");
        }

    }

    private static void showProfit(Map<String, Teacher> teacherMap, Map<String, Course> courseMap) {
        double totalTeacherSalary = 0;          // this will hold the total salary of the teachers in the map
        double totalMoneyEarned = 0;            // this will hold the total money earned of all the courses

        // iterate through the teacher map
        for (Teacher teacher : teacherMap.values()){
            // store the  teachers salary in a variable
            totalTeacherSalary += teacher.getSalary();
        }

        // iterate through the course map
        for (Course course : courseMap.values()){
            // store the money earned by courses
            totalMoneyEarned += course.getMoney_earned();
        }

        // display the profit on the console
        System.out.println("The total amount of money earned in this school is:");
        System.out.println("$" + (totalMoneyEarned - totalTeacherSalary));
    }

    // create teachers Method
    public static HashMap<String, Teacher> createTeacherMap(int n){
        String name;
        double salary;
        HashMap<String,Teacher> teacherMap = new HashMap<>();
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

    public static HashMap<String, Student> createStudentMap(int n){
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

    public static HashMap<String, Course> createCourseMap(int n){
        String name;
        double price;
        HashMap<String,Course> courseMap = new HashMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Please enter the course name:");
            name = scanner.nextLine();
            if (name == null){
                throw new NullPointerException("Course name is null!");
            }
            // TODO: check exceptions
            if (name.isEmpty()){
                throw new UnsupportedOperationException("Course name is empty");
            }
            System.out.println("Please enter the course's price: ");
            price = scanner.nextDouble();

            Course course = new Course(name, price);
            courseMap.put(course.getCourseId(), course);
            scanner.nextLine();
        }
        System.out.println("Courses were created successfully");
        return courseMap;
    }
}
