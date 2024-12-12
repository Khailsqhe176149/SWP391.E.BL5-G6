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
public class Lesson {
    private int Lessonid;
    private Date Date;
    private String Name;
    private String Content;
    private String Description;
 



    public Lesson() {
    }
    
    public Lesson(int Lessonid, Date Date, String Name, String Content, String Description) {
        this.Lessonid = Lessonid;
        this.Date = Date;
        this.Name = Name;
        this.Content = Content;
        this.Description = Description;
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

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    
}
