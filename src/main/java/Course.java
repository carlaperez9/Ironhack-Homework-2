import java.util.concurrent.atomic.AtomicInteger;

public class Course {

    //attributes
    private String courseId;
    private String name;
    private double price;
    private double money_earned;
    private static final AtomicInteger idCounter = new AtomicInteger(1000);
    private Teacher teacher;

    //constructor
    public Course(String name, double price) {
        setCourseId();
        setName(name);
        setPrice(price);
        this.money_earned = 0; //at instantiation course hasn't been purchased
        this.teacher = null; //teacher is set with a command
    }

    //Getters and setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId() {
        this.courseId = String.valueOf(idCounter.getAndIncrement()); //incrementor changes id each time a course is created
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoney_earned() {
        return money_earned;
    }

    //with each purchase, money_earned is incremented
    public void setMoney_earned() {
        this.money_earned += this.price;
    }
}
