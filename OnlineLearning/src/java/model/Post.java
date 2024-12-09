/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Khải
 */
public class Post {
    private int Postid;
    private String Title;
    private String Content;
    private Date Createtime;
    private int authorid;
    private String Img;
    private int Status;
    private int Sliderid;

    private String authorName;


    private Users author;
    private PostCategory category;

    public Post() {
    }

    public Post(int Postid, String Title, String Content, Date Createtime, int authorid, String Img, int Status, int Sliderid) {
        this.Postid = Postid;
        this.Title = Title;
        this.Content = Content;
        this.Createtime = Createtime;
        this.authorid = authorid;
        this.Img = Img;
        this.Status = Status;
        this.Sliderid = Sliderid;
    }

    public Post(int Postid, String Title, String Content, Date Createtime,  int authorid, String Img, int Status, int Sliderid, String authorName) {
        this.Postid = Postid;
        this.Title = Title;
        this.Content = Content;
        this.Createtime = Createtime;
        this.authorid = authorid;
        this.Img = Img;
        this.Status = Status;
        this.Sliderid = Sliderid;
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

   
    
    
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

    public Date getCreatetime() {
        return Createtime;
    }

    public void setCreatetime(Date Createtime) {
        this.Createtime = Createtime;
    }

    public int getAuthorid() {
        return authorid;
    }

    public void setAuthorid(int authorid) {
        this.authorid = authorid;
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

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public PostCategory getCategory() {
        return category;
    }

    public void setCategory(PostCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Post{" + "Postid=" + Postid + ", Title=" + Title + ", Content=" + Content + ", Createtime=" + Createtime + ", authorid=" + authorid + ", Img=" + Img + ", Status=" + Status + ", Sliderid=" + Sliderid + ", author=" + author + ", category=" + category + '}';
    }
    
    
}
