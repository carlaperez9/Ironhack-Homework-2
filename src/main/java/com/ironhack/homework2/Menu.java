package com.ironhack.homework2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static Map<String, Teacher> teacherMap;
    private static Map<String, Student> studentMap;
    private static Map<String, Course> courseMap;
    private static boolean exitMenu;
  
    public static void main(String[] args) {
        while(true) {
            System.out.println("************************* SCHOOL MANAGEMENT SYSTEM ****************************");
            setupSchool();
            while (exitMenu == false) {
                commandCenter(studentMap, teacherMap, courseMap);
            }
        }
    }

    //Test Fabiola
    private static void setupSchool() {
        String schoolName;
        do {
            //input validation for name not being a string fabiola
            System.out.println("Enter school name: ");
            schoolName = scanner.nextLine();
            if (schoolName.isEmpty() ) { //check string
                System.err.println("School name cannot be empty. Please try again.");
            }
        } while (schoolName.isEmpty());

        int numTeachers = getIntInput("Enter amount of teachers to create: ");
        createTeacherMap(numTeachers);

        int numCourses = getIntInput("Enter amount of courses to create: ");
        createCourseMap(numCourses);

        int numStudents = getIntInput("Enter amount of students to create: ");
        createStudentMap(numStudents);
    }

    //Test Fabiola
    private static int getIntInput(String message) {
        int num;
        do {
            System.out.print(message);
            try {
                num = Integer.parseInt(scanner.nextLine());
                if (num <= 0) {
                    System.err.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid number.");
                num = -1;
            }
        } while (num <= 0);
        return num;
    }

    //test
    public static void commandCenter(Map<String, Student> studentMap, Map<String, Teacher> teacherMap, Map<String, Course> courseMap){
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
        System.out.println("--> EXIT");
        //input validation for command
        String answer = scanner.nextLine();
        String[] commandParts = answer.split(" ");
        String command = commandParts[0];

        switch (command) {
            case "ENROLL":
                enrollStudents(commandParts[1], commandParts[2]);
                break;
            case "ASSIGN":
                assignTeacher(commandParts[1], commandParts[2]);
                break;
            case "SHOW":
                try {
                    show(commandParts[1]);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "LOOKUP":
                lookUp(commandParts[1], commandParts[2]);
                break;

            case "EXIT":
                exitMenu = true;

            default:
                System.out.println("Invalid Command");
        }
    }

    //Test Fabiola
    private static void show(String str) throws InterruptedException {
        switch (str) {
            case "COURSES":
                showCourses(courseMap);
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
        }
    }

    //Test Fabiola
    private static void lookUp(String option, String str) {
        switch (option) {
            case "COURSE":
                lookUpCourse(str, courseMap);
                break;
            case "STUDENT":
                lookUpStudent(str, studentMap);
                break;
            case "TEACHER":
                lookUpTeacher(str, teacherMap);
                break;
            default:
                System.out.println("Invalid Command");
        }
    }

    //Commands
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

    //test
    private static void lookUpCourse(String courseId, Map<String, Course> courseMap) {
        System.out.println("Course Details: \n");
        System.out.println("Course Name: " + courseMap.get(courseId).getName());
        System.out.println("Course ID: " + courseMap.get(courseId).getCourseId());
        System.out.println("Cost: " +  "$" + courseMap.get(courseId).getPrice());
        System.out.println("Profit from this course: " + "$" + courseMap.get(courseId).getMoney_earned());
        System.out.println("-----------------------------------");
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

    //test
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

    //test
    private static void lookUpTeacher(String teacherId, Map<String, Teacher> teacherMap) {
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

    //test
    private static void showProfit(Map<String, Teacher> teacherMap, Map<String, Course> courseMap) {
        double totalTeacherSalary = 0;          // this will hold the total salary of the teachers in the map
        double totalMoneyEarned = 0;            // this will hold the total money earned of all the courses

        // iterate through the teacher map
        for (Teacher teacher : teacherMap.values()){
            totalTeacherSalary += teacher.getSalary();
        }
        // iterate through the course map
        for (Course course : courseMap.values()){
            totalMoneyEarned += course.getMoney_earned();
        }
        // display the profit on the console
        System.out.println("The total amount of money earned in this school is:");
        System.out.println("$" + (totalMoneyEarned - totalTeacherSalary));
    }

    //test
    public static void createTeacherMap(int n) {
        String name;
        double salary;
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            do {
                System.out.println("Please enter the teacher name:");
                name = scanner.nextLine();
                if (name == null || name.isEmpty()) {
                    System.err.println("Teacher name cannot be empty. Please try again.");
                }
            } while (name == null || name.isEmpty());

            while (true) {
                System.out.println("Please enter the salary:");
                if (scanner.hasNextDouble()) {
                    salary = scanner.nextDouble();
                    if (salary < 0) {
                        System.err.println("Salary cannot be negative. Please enter a non-negative number.");
                    } else {
                        break;
                    }
                } else {
                    System.err.println("Invalid salary format. Please enter a valid number.");
                    scanner.next(); //clear the invalid input from the scanner buffer
                }
            }
            scanner.nextLine(); //consume the newline character left by nextDouble()
            Teacher teacher = new Teacher(name, salary);
            teacherMap.put(teacher.getTeacherId(), teacher);
        }
        System.out.println("Teachers were created successfully!");
    }

    //test
    public static void createStudentMap(int n) {
        String name, address, email;
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            do {
                System.out.println("Please enter the student name:");
                name = scanner.nextLine();
                if (name == null || name.isEmpty()) {
                    System.err.println("Student name cannot be empty. Please try again.");
                }
            } while (name == null || name.isEmpty());

            do {
                System.out.println("Please enter the student address:");
                address = scanner.nextLine();
                if (address == null || address.isEmpty()) {
                    System.err.println("Student address cannot be empty. Please try again.");
                }
            } while (address == null || address.isEmpty());

            do {
                System.out.println("Please enter the student email:");
                email = scanner.nextLine();
                if (email == null || email.isEmpty()) {
                    System.err.println("Student email cannot be empty. Please try again.");
                }
            } while (email == null || email.isEmpty());

            Student student = new Student(name, address, email);
            studentMap.put(student.getStudentId(), student);
        }
        System.out.println("Students were created successfully!");
    }

    //Test
    public static void createCourseMap(int n) {
        String name;
        double price;
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            do {
                System.out.println("Please enter the course name:");
                name = scanner.nextLine();
                if (name == null || name.isEmpty()) {
                    System.err.println("Course name cannot be empty. Please try again.");
                }
            } while (name == null || name.isEmpty());

            while (true) {
                System.out.println("Please enter the course's price:");
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price < 0) {
                        System.err.println("Price cannot be negative. Please enter a non-negative number.");
                    } else {
                        break;
                    }
                } else {
                    System.err.println("Invalid price format. Please enter a valid number.");
                    scanner.next(); //clear the invalid input from the scanner buffer
                }
            }
            scanner.nextLine(); //consume the newline character left by nextDouble()
            Course course = new Course(name, price);
            courseMap.put(course.getCourseId(), course);
        }
        System.out.println("Courses were created successfully!");
    }
}