package model;

import java.util.Date;

public class LatestPost {
    private int Postid;
    private String Title;
    private String Content;
    private Date Createdtime;
    private String AuthorName;  // Tên tác giả
    private String Img;
    private int Status;
    private int Sliderid;

    public LatestPost() {
    }

    // Constructor
    public LatestPost(int Postid, String Title, String Content, Date Createdtime, String AuthorName, String Img, int Status, int Sliderid) {
        this.Postid = Postid;
        this.Title = Title;
        this.Content = Content;
        this.Createdtime = Createdtime;
        this.AuthorName = AuthorName;
        this.Img = Img;
        this.Status = Status;
        this.Sliderid = Sliderid;
    }

    // Getters and Setters
    public int getPostid() {
        return Postid;
    }

    public void setPostid(int Postid) {
        this.Postid = Postid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public Date getCreatedtime() {
        return Createdtime;
    }

    public void setCreatedtime(Date Createdtime) {
        this.Createdtime = Createdtime;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String AuthorName) {
        this.AuthorName = AuthorName;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public int getSliderid() {
        return Sliderid;
    }

    public void setSliderid(int Sliderid) {
        this.Sliderid = Sliderid;
    }
}
