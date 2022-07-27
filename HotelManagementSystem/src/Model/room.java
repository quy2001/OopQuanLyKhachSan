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
public class room implements Serializable{
    private String roomNo;
    private String roomType;
    private int peoplePerRoom;
    private int pricePerDay;

    public room() {
        super();
    }

    public room(String roomNo, String roomType, int peoplePerRoom, int pricePerDay) {
        super();
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.peoplePerRoom = peoplePerRoom;
        this.pricePerDay = pricePerDay;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getPeoplePerRoom() {
        return peoplePerRoom;
    }

    public void setPeoplePerRoom(int peoplePerRoom) {
        this.peoplePerRoom = peoplePerRoom;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
    
    
}
