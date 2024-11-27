/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class Roles {
     private int  role_id;
    private String Rolename,Description;

    public Roles() {
    }

    public Roles(int role_id, String Rolename, String Description) {
        this.role_id = role_id;
        this.Rolename = Rolename;
        this.Description = Description;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRolename() {
        return Rolename;
    }

    public void setRolename(String Rolename) {
        this.Rolename = Rolename;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
}
