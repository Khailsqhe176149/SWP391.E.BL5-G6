/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class Quiz {
    private int Quizid;
    private String Name;
    private float miniumscore;
    private String Content;
    private String Description;
    private int Questionid;

    public Quiz() {
    }

    public Quiz(int Quizid, String Name, float miniumscore, String Content, String Description, int Questionid) {
        this.Quizid = Quizid;
        this.Name = Name;
        this.miniumscore = miniumscore;
        this.Content = Content;
        this.Description = Description;
        this.Questionid = Questionid;
    }

    public int getQuizid() {
        return Quizid;
    }

    public void setQuizid(int Quizid) {
        this.Quizid = Quizid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public float getMiniumscore() {
        return miniumscore;
    }

    public void setMiniumscore(float miniumscore) {
        this.miniumscore = miniumscore;
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

    public int getQuestionid() {
        return Questionid;
    }

    public void setQuestionid(int Questionid) {
        this.Questionid = Questionid;
    }
    
}
