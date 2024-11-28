/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class CourseRegistrater {
    private int CourseID;
    private int UserID;
    private int SubscriptionID;

    public CourseRegistrater() {
    }

    public CourseRegistrater(int CourseID, int UserID, int SubscriptionID) {
        this.CourseID = CourseID;
        this.UserID = UserID;
        this.SubscriptionID = SubscriptionID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getSubscriptionID() {
        return SubscriptionID;
    }

    public void setSubscriptionID(int SubscriptionID) {
        this.SubscriptionID = SubscriptionID;
    }
    
}
