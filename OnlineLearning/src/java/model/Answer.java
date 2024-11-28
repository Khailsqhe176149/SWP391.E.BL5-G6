/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class Answer {
    private int Answerid;
    private int Questionid;
    private String Content;
    private int Status;
    
    public Answer(){}
    
    public Answer(int Answerid, int Questionid, String Content, int Status) {
        this.Answerid = Answerid;
        this.Questionid = Questionid;
        this.Content = Content;
        this.Status = Status;
    }

    public int getAnswerid() {
        return Answerid;
    }

    public void setAnswerid(int Answerid) {
        this.Answerid = Answerid;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    
}
