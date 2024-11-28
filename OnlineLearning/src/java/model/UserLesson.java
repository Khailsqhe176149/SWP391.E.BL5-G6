/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class UserLesson {
    private int Userlessonid;
    private int Userid;
    private int Lessonid;
    private String Status;

    public UserLesson() {
    }

    public UserLesson(int Userlessonid, int Userid, int Lessonid, String Status) {
        this.Userlessonid = Userlessonid;
        this.Userid = Userid;
        this.Lessonid = Lessonid;
        this.Status = Status;
    }

    public int getUserlessonid() {
        return Userlessonid;
    }

    public void setUserlessonid(int Userlessonid) {
        this.Userlessonid = Userlessonid;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int Userid) {
        this.Userid = Userid;
    }

    public int getLessonid() {
        return Lessonid;
    }

    public void setLessonid(int Lessonid) {
        this.Lessonid = Lessonid;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
