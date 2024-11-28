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
public class Account {
    private int acc_id, role_id;
    private String email, password; 
    private Date Createdtime; 
    private int status;

    public Account() {
    }

    public Account(int acc_id, String email, String password,int status, Date Createdtime,  int role_id) {
        this.acc_id = acc_id;
        
        this.email = email;
        this.password = password;
        this.Createdtime = Createdtime;
        this.status = status;
        this.role_id = role_id;
    }

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedtime() {
        return Createdtime;
    }

    public void setCreatedtime(Date Createdtime) {
        this.Createdtime = Createdtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
