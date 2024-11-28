/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class CourseQuiz {
    private int Courseid;
    private int Quizid;

    public CourseQuiz(int Courseid, int Quizid) {
        this.Courseid = Courseid;
        this.Quizid = Quizid;
    }

    public int getCourseid() {
        return Courseid;
    }

    public void setCourseid(int Courseid) {
        this.Courseid = Courseid;
    }

    public int getQuizid() {
        return Quizid;
    }

    public void setQuizid(int Quizid) {
        this.Quizid = Quizid;
    }
    
}
