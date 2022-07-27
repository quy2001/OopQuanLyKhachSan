/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author T450s
 */
public class customer implements Serializable{
    private int idCus;
    private int idBooking;
    private int idCheckin;
    private int idCheckout;
    private String name;
    private String email;
    private String nationality;
    private String mobilePhone;
    private String gender;
    private String idProof;
    private String address;
    private String roomNo;
    private checkIN checkIn;
    private checkOUT checkOut;

    public customer() {
        super();
    }

    public customer(int idBooking, int idCheckin, int idCheckout, String name, String email, String nationality, String mobilePhone, String gender, String idProof, String address, String roomNo, checkIN checkIn, checkOUT checkOut) {
        super();
        this.idBooking = idBooking;
        this.idCheckin = idCheckin;
        this.idCheckout = idCheckout;
        this.name = name;
        this.email = email;
        this.nationality = nationality;
        this.mobilePhone = mobilePhone;
        this.gender = gender;
        this.idProof = idProof;
        this.address = address;
        this.roomNo = roomNo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getIdCus() {
        return idCus;
    }

    public void setIdCus(int idCus) {
        this.idCus = idCus;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getIdCheckin() {
        return idCheckin;
    }

    public void setIdCheckin(int idCheckin) {
        this.idCheckin = idCheckin;
    }

    public int getIdCheckout() {
        return idCheckout;
    }

    public void setIdCheckout(int idCheckout) {
        this.idCheckout = idCheckout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public checkIN getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(checkIN checkIn) {
        this.checkIn = checkIn;
    }

    public checkOUT getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(checkOUT checkOut) {
        this.checkOut = checkOut;
    }
    
    

    
}
