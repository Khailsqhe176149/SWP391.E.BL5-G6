/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class PostCategory {
    private int postCatPostidegoryId;
    private int Postid;
    private String Name;
    private int Status;

    public PostCategory() {
    }

    public PostCategory(int postCatPostidegoryId, int Postid, String Name, int Status) {
        this.postCatPostidegoryId = postCatPostidegoryId;
        this.Postid = Postid;
        this.Name = Name;
        this.Status = Status;
    }

    public int getPostCatPostidegoryId() {
        return postCatPostidegoryId;
    }

    public void setPostCatPostidegoryId(int postCatPostidegoryId) {
        this.postCatPostidegoryId = postCatPostidegoryId;
    }

    public int getPostid() {
        return Postid;
    }

    public void setPostid(int Postid) {
        this.Postid = Postid;
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
    
}
