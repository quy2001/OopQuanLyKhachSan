/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.booking;
import Model.checkIN;
import Model.checkOUT;
import Model.customer;
import Model.detailBooking;
import Model.room;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author T450s
 */
public class bookingDAO extends DAO{

    public bookingDAO() {
        super();
    }
    
    
    
    public ArrayList<room> checkRoomNotBooked(Date cinDate, Date coutDate)
    {
        ArrayList<room> result = new ArrayList<>();
        String sql = "select room.* from room  inner join detailBooking  on room.roomNo = detailBooking.roomNo  where status not in ('Cancel', 'Checked Out') and not(checkoutDate >= ? and checkinDate <= ?) order by room.roomNo";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, cinDate);
            ps.setDate(2, coutDate);
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
    
    
    public boolean addBooking(booking b, detailBooking db)
    {
        int id = 1;
        String sqlAddBooking = "insert into booking values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlAddDetailBooking = "insert into detailBooking values (?, ?, ?, ?, ?, ?)";
        String sql = "select max(idBooking) from booking";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                id = rs.getInt(1);
            }
            id = id + 1;
            
            
            ps = con.prepareStatement(sqlAddBooking);
            //ps.setInt(1, b.getIdBooking());
            ps.setInt(1, id);
            ps.setString(2, b.getName());
            ps.setString(3, b.getEmail());
            ps.setString(4, b.getNationality());
            ps.setString(5, b.getMobilePhone());
            ps.setDate(6, Date.valueOf(b.getBookingDate()));
            ps.setString(7, b.getBookingTime());
            ps.setString(8, b.getGuestName());
            ps.setString(9, b.getGuestEmail());
            ps.setString(10, b.getCardholderName());
            ps.setString(11, b.getCardType());
            ps.setString(12, b.getCardNumber());
            ps.setDate(13, Date.valueOf(b.getExpirationDate()));
            ps.executeUpdate();

            
            ps = con.prepareStatement(sqlAddDetailBooking);
            ps.setInt(1, id);
            ps.setString(2, db.getRoomNo());
            ps.setDate(3, Date.valueOf(db.getCheckinDate()));
            ps.setDate(4, Date.valueOf(db.getCheckoutDate()));
            ps.setString(5, "Booked");
            ps.setDate(6, null);
            ps.executeUpdate();
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public booking searchBooking(String roomNo, Date checkinDate)
    {
        booking b = null;
        String sql = "select * from detailBooking a inner join booking b on a.idBooking = b.idBooking where roomNo = ? and checkinDate = ? and status = 'Booked'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ps.setDate(2, checkinDate);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                b.setGuestName(rs.getString("guestName"));
                b.setGuestEmail(rs.getString("guestEmail"));
                b.setNationality(rs.getString("nationality"));
                b.setMobilePhone(rs.getString("mobilePhone"));
                
                detailBooking db = new detailBooking();
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                db.setRoomNo(rs.getString("roomNo"));
                b.setDetailbooking(db);
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect Email!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return b;
    }
    
    public boolean updateBookingDetail(detailBooking db)
    {
        String sql = "update detailBooking set status = 'Checked In'  where idBooking = ?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, db.getIdBooking());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public customer searchCheckinRoom(String roomNo)
    {
        customer cus = null;
        String sql = "select * from customer a inner join detailBooking b on a.idBooking = b.idBooking where b.roomNo = ? and status = 'Checked in'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cus = new customer();
                cus.setName(rs.getString("name"));
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
        return cus;
    }
    
    
    
    public ArrayList<booking> showBooking()
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select a.* from booking a, detailBooking b where a.idBooking = b.idBooking and status = 'Booked' and bookingTime not in ('19:45')";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                b.setBookingTime(rs.getString("bookingTime"));
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<detailBooking> showDetailBooking()
    {
        ArrayList<detailBooking> result = new ArrayList<>();
        String sql = "select a.* from detailBooking a, booking b where a.idBooking = b.idBooking and status = 'Booked' and bookingTime not in ('19:45')";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                detailBooking db = new detailBooking();
                db.setRoomNo(rs.getString("roomNo"));
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                result.add(db);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<booking> showBooking1()
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select a.* from booking a, detailBooking b where a.idBooking = b.idBooking and status not in ('Cancel') and bookingTime not in ('19:45')";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                b.setBookingTime(rs.getString("bookingTime"));
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<detailBooking> showDetailBooking1()
    {
        ArrayList<detailBooking> result = new ArrayList<>();
        String sql = "select a.* from detailBooking a, booking b where a.idBooking = b.idBooking and status not in ('Cancel') and bookingTime not in ('19:45')";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                detailBooking db = new detailBooking();
                db.setRoomNo(rs.getString("roomNo"));
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                db.setStatus(rs.getString("status"));
                result.add(db);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<booking> searchBooking(String email)
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select a.* from booking a, detailBooking b where a.idBooking = b.idBooking and status not in ('Cancel') and email = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                b.setBookingTime(rs.getString("bookingTime"));
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<detailBooking> searchDetailBooking(String email)
    {
        ArrayList<detailBooking> result = new ArrayList<>();
        String sql = "select a.* from detailBooking a inner join booking b on a.idBooking = b.idBooking where status not in ('Cancel') and b.email = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                detailBooking db = new detailBooking();
                db.setRoomNo(rs.getString("roomNo"));
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                db.setStatus(rs.getString("status"));
                result.add(db);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public booking searchBookingById(int idBooking)
    {
        booking b = null;
        String sql = "select * from booking where idBooking = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBooking);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setNationality(rs.getString("nationality"));
                b.setMobilePhone(rs.getString("mobilePhone"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                b.setBookingTime(rs.getString("bookingTime"));
                b.setGuestName(rs.getString("guestName"));
                b.setGuestEmail(rs.getString("guestEmail"));
                b.setCardholderName(rs.getString("cardholderName"));
                b.setCardType(rs.getString("cardType"));
                b.setCardNumber(rs.getString("cardNumber"));
                b.setExpirationDate(rs.getDate("expirationDate").toLocalDate());
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
        return b;
    }
    
    public detailBooking searchDetailBookingById(int idBooking)
    {
        detailBooking db = null;
        String sql = "select * from detailBooking where idBooking = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBooking);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                db = new detailBooking();
                db.setRoomNo(rs.getString("roomNo"));
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                db.setStatus(rs.getString("status"));
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
        return db;
    }
    
    public boolean cancelBooking(detailBooking db)
    {
        String sql = "update detailBooking set status = 'Cancel', canceldate = ? where idBooking = ? and status = 'Booked'";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, db.getCancelDate().toString());
            ps.setInt(2, db.getIdBooking());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public booking searchBookingForCheckout(String roomNo, Date checkoutDate)
    {
        booking b = null;
        String sql = "select * from detailBooking a inner join booking b on a.idBooking = b.idBooking where roomNo = ? and checkoutDate = ? and status = 'Checked In'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ps.setDate(2, checkoutDate);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
//                b.setName(rs.getString("name"));
//                b.setEmail(rs.getString("email"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
//                b.setGuestName(rs.getString("guestName"));
//                b.setGuestEmail(rs.getString("guestEmail"));
//                b.setNationality(rs.getString("nationality"));
//                b.setMobilePhone(rs.getString("mobilePhone"));
                
                detailBooking db = new detailBooking();
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setRoomNo(rs.getString("roomNo"));
                b.setDetailbooking(db);
                
            }
//            else
//            {
//                JOptionPane.showMessageDialog(null, "Incorrect Email!");
//            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return b;
    }
    
    public boolean updateForCheckout(detailBooking db)
    {
        String sql = "update detailBooking set status = 'Checked Out' where idBooking = ?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, db.getIdBooking());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
//    public detailBooking getCount(String roomNo)
//    {
//        detailBooking db = null;
//        String sql = "select count(roomNo) as Qty from detailBooking where roomNo = ? and status not in ('Cancel Book', 'Checked Out') group by roomNo";
//        try
//        {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, roomNo);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next())
//            {
//                db = new detailBooking();
//                db.setRoomNo(rs.getString("roomNo"));
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return db;
//    }
    
    public int getCount(String roomNo)
    {
        int count = 0;
        String sql = "select count(roomNo) as Qty from detailBooking where roomNo = ? and status not in ('Cancel', 'Checked Out') group by roomNo";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
//                db = new detailBooking();
//                db.setRoomNo(rs.getString("roomNo"));
                count = rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return count;
    }
    
    public ArrayList<booking> searchBooking(String name, String email, Date checkinDate)
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select * from detailBooking a inner join booking b on a.idBooking = b.idBooking where name = ? and email = ? and checkinDate = ? and status = 'Booked'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setDate(3, checkinDate);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                booking b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                b.setGuestName(rs.getString("guestName"));
                b.setGuestEmail(rs.getString("guestEmail"));
                b.setNationality(rs.getString("nationality"));
                b.setMobilePhone(rs.getString("mobilePhone"));
                
                detailBooking db = new detailBooking();
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                db.setRoomNo(rs.getString("roomNo"));
                b.setDetailbooking(db);
                result.add(b); 
            }

        }
        catch(Exception e)
        {
            //JOptionPane.showMessageDialog(null, "Incorrect Email/Name!");
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<customer> showCheckoutCus(Date sDate, Date eDate)
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select a.idCheckout, a.name, a.email, a.roomNO, b.checkoutDate, b.totalPrice from customer a, checkout b, detailBooking d where a.idCheckout = b.idCheckout and b.idBooking =  d.idBooking and b.checkoutDate >= ? and b.checkoutDate <= ? and status = 'Checked Out'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setIdCheckout(rs.getInt("idCheckout"));
                cus.setName(rs.getString("name"));
                cus.setEmail(rs.getString("email"));
                cus.setRoomNo(rs.getString("roomNo"));
                
                checkOUT co = new checkOUT();
                co.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                co.setTotalPrice(rs.getInt("totalPrice"));
                cus.setCheckOut(co);
                result.add(cus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<booking> showCheckoutBooking(Date sDate, Date eDate)
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select c.bookingDate, d.checkinDate from customer a, checkout b, booking c, detailBooking d where a.idCheckout = b.idCheckout and b.idBooking = c.idBooking and c.idBooking = d.idBooking and b.checkoutDate >= ? and b.checkoutDate <= ? and status = 'Checked Out'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                
                detailBooking db = new detailBooking();
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                b.setDetailbooking(db);
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<customer> showCheckoutCus()
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select b.idCheckout, a.name, a.email, a.roomNO, b.checkoutDate, b.totalPrice from customer a, checkout b, detailBooking d where a.idCheckout = b.idCheckout and b.idBooking =  d.idBooking and status = 'Checked Out'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setIdCheckout(rs.getInt("idCheckout"));
                cus.setName(rs.getString("name"));
                cus.setEmail(rs.getString("email"));
                cus.setRoomNo(rs.getString("roomNo"));
                
                checkOUT co = new checkOUT();
                co.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                co.setTotalPrice(rs.getInt("totalPrice"));
                cus.setCheckOut(co);
                result.add(cus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<booking> showCheckoutBooking()
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select c.bookingDate, d.checkinDate from customer a, checkout b, booking c, detailBooking d where a.idCheckout = b.idCheckout and b.idBooking = c.idBooking and c.idBooking = d.idBooking and status = 'Checked Out'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                
                detailBooking db = new detailBooking();
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                b.setDetailbooking(db);
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<booking> showBooking(Date sDate, Date eDate)
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select a.* from booking a, detailBooking b where a.idBooking = b.idBooking and status not in ('Cancel') and bookingTime not in ('19:45') and bookingDate >= ? and bookingDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                b.setIdBooking(rs.getInt("idBooking"));
                b.setName(rs.getString("name"));
                b.setEmail(rs.getString("email"));
                b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                b.setBookingTime(rs.getString("bookingTime"));
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<detailBooking> showDetailBooking(Date sDate, Date eDate)
    {
        ArrayList<detailBooking> result = new ArrayList<>();
        String sql = "select a.* from detailBooking a, booking b where a.idBooking = b.idBooking and status not in ('Cancel') and bookingTime not in ('19:45') and bookingDate >= ? and bookingDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                detailBooking db = new detailBooking();
                db.setRoomNo(rs.getString("roomNo"));
                db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                db.setStatus(rs.getString("status"));
                result.add(db);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<customer> showCheckinCus()
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select * from customer a, checkin b, detailBooking d where a.idCheckin = b.idCheckin and b.idBooking =  d.idBooking and status = 'Checked In'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setIdCheckin(rs.getInt("idCheckin"));
                cus.setIdCus(rs.getInt("idCus"));
                cus.setName(rs.getString("name"));
                cus.setEmail(rs.getString("email"));
                cus.setRoomNo(rs.getString("roomNo"));
                
                checkIN ci = new checkIN();
                ci.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                cus.setCheckIn(ci);
                result.add(cus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<booking> showCheckinBooking()
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select c.bookingDate, d.checkoutDate from customer a, checkin b, booking c, detailBooking d where a.idCheckin = b.idCheckin and b.idBooking = c.idBooking and c.idBooking = d.idBooking and status = 'Checked In'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                //b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                
                detailBooking db = new detailBooking();
                //db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                b.setDetailbooking(db);
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<customer> showCheckinCus(String roomNo)
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select * from customer a, checkin b, detailBooking d where a.idCheckin = b.idCheckin and b.idBooking =  d.idBooking and status = 'Checked In' and a.roomNo = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setIdCheckin(rs.getInt("idCheckin"));
                cus.setIdCus(rs.getInt("idCus"));
                cus.setName(rs.getString("name"));
                cus.setEmail(rs.getString("email"));
                cus.setRoomNo(rs.getString("roomNo"));
                
                checkIN ci = new checkIN();
                ci.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                cus.setCheckIn(ci);
                result.add(cus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<booking> showCheckinBooking(String roomNo)
    {
        ArrayList<booking> result = new ArrayList<>();
        String sql = "select c.bookingDate, d.checkoutDate from customer a, checkin b, booking c, detailBooking d where a.idCheckin = b.idCheckin and b.idBooking = c.idBooking and c.idBooking = d.idBooking and status = 'Checked In' and a.roomNo = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                booking b = new booking();
                //b.setBookingDate(rs.getDate("bookingDate").toLocalDate());
                
                detailBooking db = new detailBooking();
                //db.setCheckinDate(rs.getDate("checkinDate").toLocalDate());
                db.setCheckoutDate(rs.getDate("checkoutDate").toLocalDate());
                b.setDetailbooking(db);
                result.add(b);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
