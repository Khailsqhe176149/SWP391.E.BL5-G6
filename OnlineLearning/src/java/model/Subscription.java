/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Khải
 */
public class Subscription {
    private int SubscriptionID;
    private String Package;
    private float Price;

    public Subscription() {
    }

    public Subscription(int SubscriptionID, String Package, float Price) {
        this.SubscriptionID = SubscriptionID;
        this.Package = Package;
        this.Price = Price;
    }

    public int getSubscriptionID() {
        return SubscriptionID;
    }

    public void setSubscriptionID(int SubscriptionID) {
        this.SubscriptionID = SubscriptionID;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String Package) {
        this.Package = Package;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
    
}
