/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Khải
 */
public class Course {

    private int courseId;

    private String name;
    private int subjectid;
    private double price;
    private int authorId;
    private String description;
    private String img;
    private Date createdTime;
    private int status;
    private String tag;
    private Date RegistrationDate;
    private String SubjectName;
    private List<Lesson> lessons;

    public Course() {
    }

    public Course(int courseId, String name, double price, int status, String SubjectName) {
        this.courseId = courseId;
        this.name = name;
        this.price = price;
        this.status = status;
        this.SubjectName = SubjectName;
    }

    // Getters and Setters
    public Course(int courseId, String name, int subjectid, double price, String description, String img, int status) {
        this.courseId = courseId;
        this.name = name;
        this.subjectid = subjectid;
        this.price = price;
        this.description = description;
        this.img = img;
        this.status = status;
    }

    public Course(int courseId, String name, String description, double price, int status, String img) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.img = img;
    }

    public Course(int courseId, String name, String description, double price, String img) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public Course(int courseId, String name, int subjectid, double price, int authorId, String description, String img, Date createdTime, int status, String tag) {
        this.courseId = courseId;
        this.name = name;
        this.subjectid = subjectid;
        this.price = price;
        this.authorId = authorId;
        this.description = description;
        this.img = img;
        this.createdTime = createdTime;
        this.status = status;
        this.tag = tag;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setRegistrationDate(Date RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }
    
        public List<Lesson> getLessons() {
    return lessons;
}

public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
}

}
