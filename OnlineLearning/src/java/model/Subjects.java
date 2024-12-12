/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class Subjects {

    private int subjectid;
    private String Name;
    private int Status;
    private int courseCount;

    public Subjects() {
    }

    public Subjects(int subjectid, String Name) {
        this.subjectid = subjectid;
        this.Name = Name;
    }

    public Subjects(int subjectid, String Name, int Status) {
        this.subjectid = subjectid;
        this.Name = Name;
        this.Status = Status;
    }

    public int getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(int subjectid) {
        this.subjectid = subjectid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    @Override
    public String toString() {
        return "Subjects{" + "subjectid=" + subjectid + ", Name=" + Name + ", Status=" + Status + ", courseCount=" + courseCount + '}';
    }

}
