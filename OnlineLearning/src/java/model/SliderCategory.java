/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class SliderCategory {
    private int Slidercategoryid;
    private int Status;
    private String Name;

    public SliderCategory() {
    }

    public SliderCategory(int Slidercategoryid, int Status, String Name) {
        this.Slidercategoryid = Slidercategoryid;
        this.Status = Status;
        this.Name = Name;
    }

    public int getSlidercategoryid() {
        return Slidercategoryid;
    }

    public void setSlidercategoryid(int Slidercategoryid) {
        this.Slidercategoryid = Slidercategoryid;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
}
