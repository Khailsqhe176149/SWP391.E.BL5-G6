/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class Question {
    private int Questionid;
    private String Content;

    public Question() {
    }

    public Question(int Questionid, String Content) {
        this.Questionid = Questionid;
        this.Content = Content;
    }

    public int getQuestionid() {
        return Questionid;
    }

    public void setQuestionid(int Questionid) {
        this.Questionid = Questionid;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
    
}
