package model;

public class CountCourse {
    private int courseId;
    private String courseName;
    private int numberOfRegistrations;
    private double price;  // Thêm trường giá
    private String img;    // Thêm trường ảnh

    // Constructor
    public CountCourse(int courseId, String courseName, int numberOfRegistrations, double price, String img) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.numberOfRegistrations = numberOfRegistrations;
        this.price = price;
        this.img = img;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumberOfRegistrations() {
        return numberOfRegistrations;
    }

    public void setNumberOfRegistrations(int numberOfRegistrations) {
        this.numberOfRegistrations = numberOfRegistrations;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
