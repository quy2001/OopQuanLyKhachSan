/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DAO.con;
import Model.detailBooking;
import Model.room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Date;

/**
 *
 * @author T450s
 */
public class roomDAO extends DAO{

    public roomDAO() {
        super();
    }
    
    public ArrayList<room> showRooms()
    {
        ArrayList<room> result = new ArrayList<>();
        String sql = "select * from room";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                room rm = new room();
                rm.setRoomNo(rs.getString("roomNo"));
                rm.setRoomType(rs.getString("roomType"));
                rm.setPeoplePerRoom(rs.getInt("peoplePerRoom"));
                rm.setPricePerDay(rs.getInt("pricePerDay"));
                result.add(rm);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public room searchRoom(String roomNo)
    {
        room rm = null;
        String sql = "select * from room where roomNo = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                rm = new room();
                rm.setRoomNo(rs.getString("roomNo"));
                rm.setRoomType(rs.getString("roomType"));
                rm.setPeoplePerRoom(rs.getInt("peoplePerRoom"));
                rm.setPricePerDay(rs.getInt("pricePerDay"));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect Room Number!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return rm;
    }
    
    public boolean updateRoom(room rm)
    {
        String sql = "update room set roomType = ?, peoplePerRoom = ?, pricePerDay = ?  where roomNo = ?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, rm.getRoomType());
            ps.setInt(2, rm.getPeoplePerRoom());
            ps.setInt(3, rm.getPricePerDay());
            ps.setString(4, rm.getRoomNo());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ArrayList<room> showRoom1()
    {
        ArrayList<room> result = new ArrayList<>();
        String sql = "select * from room";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                room r = new room();
                r.setRoomNo(rs.getString("roomNo"));
                r.setRoomType(rs.getString("roomType"));
                r.setPeoplePerRoom(rs.getInt("peoplePerRoom"));
                r.setPricePerDay(rs.getInt("pricePerDay"));
                result.add(r);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<detailBooking> detailRoom()
    {
        ArrayList<detailBooking> result = new ArrayList<>();
        String sql = "select Count(roomNo) - 1 as qty from detailBooking where roomNo in (select roomNo from room) and status not in ('Cancel') group by roomNo";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                detailBooking db = new detailBooking();
                db.setRoomNo(rs.getString("qty"));
                result.add(db);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<room> showRoom1(Date sDate, Date eDate)
    {
        ArrayList<room> result = new ArrayList<>();
        String sql = "select distinct a.* from room a, detailBooking b, booking c where a.roomNo = b.roomNo and b.idBooking = c.idBooking and bookingDate >= ? and bookingDate <= ? and status not in ('Cancel')";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                room r = new room();
                r.setRoomNo(rs.getString("roomNo"));
                r.setRoomType(rs.getString("roomType"));
                r.setPeoplePerRoom(rs.getInt("peoplePerRoom"));
                r.setPricePerDay(rs.getInt("pricePerDay"));
                result.add(r);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<detailBooking> detailRoom(Date sDate, Date eDate)
    {
        ArrayList<detailBooking> result = new ArrayList<>();
        String sql = "select Count(roomNo) as qty from detailBooking a, booking b where a.idBooking = b.idBooking and roomNo in (select roomNo from room) and status not in ('Cancel') and bookingDate >= ? and bookingDate <= ? group by roomNo";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                detailBooking db = new detailBooking();
                db.setRoomNo(rs.getString("qty"));
                result.add(db);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
}
