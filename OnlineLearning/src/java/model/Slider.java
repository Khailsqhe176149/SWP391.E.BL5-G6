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
public class Slider {
    private int Sliderid;
    private int Status;
    private String Img;
    private String Title;
    private String Description;
    private Date Createtime;
    private int slidercategoryid;

    public Slider() {
    }

    public Slider(int Sliderid, int Status, String Img, String Title, String Description, Date Createtime, int slidercategoryid) {
        this.Sliderid = Sliderid;
        this.Status = Status;
        this.Img = Img;
        this.Title = Title;
        this.Description = Description;
        this.Createtime = Createtime;
        this.slidercategoryid = slidercategoryid;
    }

    public int getSliderid() {
        return Sliderid;
    }

    public void setSliderid(int Sliderid) {
        this.Sliderid = Sliderid;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getCreatetime() {
        return Createtime;
    }

    public void setCreatetime(Date Createtime) {
        this.Createtime = Createtime;
    }

    public int getSlidercategoryid() {
        return slidercategoryid;
    }

    public void setSlidercategoryid(int slidercategoryid) {
        this.slidercategoryid = slidercategoryid;
    }
    
}
