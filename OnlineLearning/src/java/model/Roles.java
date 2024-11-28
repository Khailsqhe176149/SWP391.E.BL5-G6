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

    private int Roleid;
    private String Rolename;
    private String Description;

    public Roles() {
    }

    public Roles(int Roleid, String Rolename, String Description) {
        this.Roleid = Roleid;
        this.Rolename = Rolename;
        this.Description = Description;
    }

    public int getRoleid() {
        return Roleid;
    }

    public void setRoleid(int Roleid) {
        this.Roleid = Roleid;
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
