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
public class checkIN implements Serializable{
    private int idCheckin;
    private LocalDate checkinDate;
    private String checkinTime;
    private int idBooking;

    public checkIN() {
        super();
    }

    public checkIN(LocalDate checkinDate, String checkinTime, int idBooking) {
        super();
        this.checkinDate = checkinDate;
        this.checkinTime = checkinTime;
        this.idBooking = idBooking;
    }

    public int getIdCheckin() {
        return idCheckin;
    }

    public void setIdCheckin(int idCheckin) {
        this.idCheckin = idCheckin;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }
        
    
    
}
