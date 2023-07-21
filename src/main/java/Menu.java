import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

    }

    public static void commandCenter(){
        String command = scanner.nextLine();
        switch(command){
            case "ENROLL":
                enrollStudents(studentId, courseId);
                break;
            case "ASSIGN":
                assignTeacher(teacherId, courseId);
                break;
            case "SHOW COURSES":
                showCourses();
                break;
            case "LOOKUP COURSE":
                lookUpCourse(courseId);
                break;
            case "SHOW STUDENTS":
                showStudents();
                break;
            case "LOOKUP STUDENT":
                lookUpStudent(studentId);
                break;
            case "SHOW TEACHERS":
                showTeachers();
                break;
            case "LOOKUP TEACHER":
                lookUpTeacher(teacherId);
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


}
