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
public class Users {
    private int acc_id, user_id;
    private String Name,Phone,Img,Address; 
    private Date  Dob; 
    private int Gender;

    public Users() {
    }

    public Users(int acc_id, int user_id, String Name, String Phone, String Img, String Address, Date Dob, int Gender) {
        this.acc_id = acc_id;
        this.user_id = user_id;
        this.Name = Name;
        this.Phone = Phone;
        this.Img = Img;
        this.Address = Address;
        this.Dob = Dob;
        this.Gender = Gender;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date Dob) {
        this.Dob = Dob;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }
    
}
