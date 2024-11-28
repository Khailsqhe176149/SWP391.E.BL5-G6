/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Khải
 */
public class CourseLesson {
    private int Courseid;
    private int Lessonid;
    private Date Date;

    public CourseLesson(int Courseid, int Lessonid, Date Date) {
        this.Courseid = Courseid;
        this.Lessonid = Lessonid;
        this.Date = Date;
    }

    public int getCourseid() {
        return Courseid;
    }

    public void setCourseid(int Courseid) {
        this.Courseid = Courseid;
    }

    public int getLessonid() {
        return Lessonid;
    }

    public void setLessonid(int Lessonid) {
        this.Lessonid = Lessonid;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
    
    
}
