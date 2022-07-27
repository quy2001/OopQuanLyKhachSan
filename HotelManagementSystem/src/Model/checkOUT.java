/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author T450s
 */
public class checkOUT implements Serializable{
    private int idCheckout;
    private LocalDate checkoutDate;
    private String checkoutTime;
    private int idBooking;
    private int servicesPrice;
    private int totalPrice;

    public checkOUT() {
        super();
    }

    public checkOUT(LocalDate checkoutDate, String checkoutTime, int idBooking, int servicesPrice, int totalPrice) {
        super();
        this.checkoutDate = checkoutDate;
        this.checkoutTime = checkoutTime;
        this.idBooking = idBooking;
        this.servicesPrice = servicesPrice;
        this.totalPrice = totalPrice;
    }

    public int getIdCheckout() {
        return idCheckout;
    }

    public void setIdCheckout(int idCheckout) {
        this.idCheckout = idCheckout;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getServicesPrice() {
        return servicesPrice;
    }

    public void setServicesPrice(int servicesPrice) {
        this.servicesPrice = servicesPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
    
    
}
