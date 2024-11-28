/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class UserQuiz {
    private int Userquizid;
    private int Userid;
    private int Quizid;
    private float Score;
    private String Status;

    public UserQuiz() {
    }

    public UserQuiz(int Userquizid, int Userid, int Quizid, float Score, String Status) {
        this.Userquizid = Userquizid;
        this.Userid = Userid;
        this.Quizid = Quizid;
        this.Score = Score;
        this.Status = Status;
    }

    public int getUserquizid() {
        return Userquizid;
    }

    public void setUserquizid(int Userquizid) {
        this.Userquizid = Userquizid;
    }

    public int getUserid() {
        return Userid;
    }

    public void setUserid(int Userid) {
        this.Userid = Userid;
    }

    public int getQuizid() {
        return Quizid;
    }

    public void setQuizid(int Quizid) {
        this.Quizid = Quizid;
    }

    public float getScore() {
        return Score;
    }

    public void setScore(float Score) {
        this.Score = Score;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}
