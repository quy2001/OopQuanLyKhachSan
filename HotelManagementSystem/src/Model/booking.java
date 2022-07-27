/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author T450s
 */
public class booking implements Serializable{
    private int idBooking;
    private String name;
    private String email;
    private String nationality;
    private String mobilePhone;
    private LocalDate bookingDate;
    private String bookingTime;
    private String guestName;
    private String guestEmail;
    private String cardholderName;
    private String cardType;
    private String cardNumber;
    private LocalDate expirationDate;
    private detailBooking detailbooking;

    public booking() {
        super();
    }

    public booking(String name, String email, String nationality, String mobilePhone, LocalDate bookingDate, String bookingTime, String guestName, String guestEmail, String cardholderName, String cardType, String cardNumber, LocalDate expirationDate, detailBooking detailbooking) {
        super();
        this.name = name;
        this.email = email;
        this.nationality = nationality;
        this.mobilePhone = mobilePhone;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.cardholderName = cardholderName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.detailbooking = detailbooking;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
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

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public detailBooking getDetailbooking() {
        return detailbooking;
    }

    public void setDetailbooking(detailBooking detailbooking) {
        this.detailbooking = detailbooking;
    }
    

    
    
    
}
