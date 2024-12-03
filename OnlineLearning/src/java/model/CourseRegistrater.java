package model;

import java.util.Date;

/**
 * CourseRegistrater model class used to represent the registration of a user to a course.
 */
public class CourseRegistrater {
    private int CourseID;
    private int UserID;
    private int Status;               // Trạng thái đăng ký (1 = Đã đăng ký, 2 = Đã thanh toán, 3 = Đã hủy)
    private Date RegistrationDate; // Ngày đăng ký khóa học
    
    // Constructor mặc định
    public CourseRegistrater() {
    }

    // Constructor với các tham số
    public CourseRegistrater(int CourseID, int UserID, int Status, java.sql.Date RegistrationDate) {
        this.CourseID = CourseID;
        this.UserID = UserID;
        this.Status = Status;
        this.RegistrationDate = RegistrationDate;
    }

    // Getter và Setter cho CourseID
    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    // Getter và Setter cho UserID
    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    // Getter và Setter cho Status
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    // Getter và Setter cho RegistrationDate
    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public void setRegistrationDate(java.sql.Date RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }

    @Override
    public String toString() {
        return "CourseRegistrater{" +
                "CourseID=" + CourseID +
                ", UserID=" + UserID +
                ", Status=" + Status +
                ", RegistrationDate=" + RegistrationDate +
                '}';
    }
}
