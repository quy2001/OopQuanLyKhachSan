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
public class detailBooking implements Serializable{
    private int idBooking;
    private String roomNo;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private String status;
    private LocalDate cancelDate;
    private room rm;

    public detailBooking() {
        super();
    }

    public detailBooking(int idBooking, String roomNo, LocalDate checkinDate, LocalDate checkoutDate, String status, LocalDate cancelDate, room rm) {
        super();
        this.idBooking = idBooking;
        this.roomNo = roomNo;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.status = status;
        this.cancelDate = cancelDate;
        this.rm = rm;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDate cancelDate) {
        this.cancelDate = cancelDate;
    }

    public room getRm() {
        return rm;
    }

    public void setRm(room rm) {
        this.rm = rm;
    }
    
    

    
    
    
}
