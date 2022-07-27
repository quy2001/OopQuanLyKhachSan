/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DAO.con;
import Model.checkIN;
import Model.checkOUT;
import Model.customer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author T450s
 */
public class customerDAO extends DAO{

    public customerDAO() {
        super();
    }
    
    
    public boolean addCheckin(checkIN cin)
    {
        int idCheckin = 1;
        String sqlAddCheckin = "insert into checkin values (?, ?, ?, ?)";
        String sql = "select max(idCheckin) from checkin";
        
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                idCheckin = rs.getInt(1);
            }
            idCheckin = idCheckin + 1;
            
            ps = con.prepareStatement(sqlAddCheckin);
            ps.setInt(1, idCheckin);
            ps.setDate(2, Date.valueOf(cin.getCheckinDate()));
            ps.setString(3, cin.getCheckinTime());
            ps.setInt(4, cin.getIdBooking());
            ps.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    
    public boolean addCustomer(customer cus)
    {
        int idCus = 1;
        String sqlAddCustomer = "insert into customer values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sql1 = "select max(idCus) from customer";
        
        try
        {
            PreparedStatement ps = con.prepareStatement(sql1);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                idCus = rs.getInt(1);
            }
            idCus = idCus + 1;
            ps = con.prepareStatement(sqlAddCustomer);
            ps.setInt(1, idCus);
            ps.setInt(2, cus.getIdBooking());
            ps.setInt(3, cus.getIdCheckin());
            ps.setInt(4, 0);
            ps.setString(5, cus.getName());
            ps.setString(6, cus.getEmail());
            ps.setString(7, cus.getNationality());
            ps.setString(8, cus.getMobilePhone());
            ps.setString(9, cus.getGender());
            ps.setString(10, cus.getIdProof());
            ps.setString(11, cus.getAddress());
            ps.setString(12, cus.getRoomNo());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public checkIN searchCheckin(int idBooking)
    {
        checkIN cin = null;
        String sql = "select * from checkin where idBooking = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBooking);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cin = new checkIN();
                cin.setIdCheckin(rs.getInt("idCheckin"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cin;
    }
    
    public customer searchCustomer(String name, String roomNo)
    {
        customer cus = null;
        String sql = "select * from customer a inner join detailBooking b on a.idBooking = b.idBooking where b.roomNo = ? and a.name = ? and status = 'Checked In'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cus = new customer();
                cus.setIdCus(rs.getInt("idCus"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cus;
    }
    
    public ArrayList<customer> searchCustomer(int idBooking)
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select * from customer where idBooking = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBooking);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setIdCus(rs.getInt("idCus"));
                cus.setIdBooking(rs.getInt("idBooking"));
                cus.setName(rs.getString("name"));
                cus.setNationality(rs.getString("nationality"));
                cus.setGender(rs.getString("gender"));
                cus.setMobilePhone(rs.getString("mobilePhone"));
                cus.setEmail(rs.getString("email"));
                cus.setIdProof(rs.getString("idProof"));
                cus.setAddress(rs.getString("address"));
                cus.setRoomNo(rs.getString("roomNo"));
                result.add(cus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public customer searchCustomer(String roomNo, Date cinDate, Date coutDate)
    {
        customer cus = null;
        String sql = "select top(1) idCus from customer a inner join detailBooking b on a.idBooking = b.idBooking where b.roomNo = ? and checkinDate = ? and checkoutDate = ? and status = 'Checked In'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNo);
            ps.setDate(2, cinDate);
            ps.setDate(3, coutDate);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cus = new customer();
                cus.setIdCus(rs.getInt("idCus"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cus;
    }
    
    public boolean addCheckout(checkOUT cout)
    {
        int idCheckout = 1;
        String sqlAddCheckout = "insert into checkout values (?, ?, ?, ?, ?, ?)";
        String sql = "select max(idCheckout) from checkout";
        
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                idCheckout = rs.getInt(1);
            }
            idCheckout = idCheckout + 1;
            
            ps = con.prepareStatement(sqlAddCheckout);
            ps.setInt(1, idCheckout);
            ps.setDate(2, Date.valueOf(cout.getCheckoutDate()));
            ps.setString(3, cout.getCheckoutTime());
            ps.setInt(4, cout.getIdBooking());
            ps.setInt(5, cout.getServicesPrice());
            ps.setInt(6, cout.getTotalPrice());
            ps.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public checkOUT searchCheckout(int idBooking)
    {
        checkOUT cout = null;
        String sql = "select * from checkout where idBooking = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBooking);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cout = new checkOUT();
                cout.setIdCheckout(rs.getInt("idCheckout"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cout;
    }
    
    public boolean updateCheckout(customer cus)
    {
        String sql = "update customer set idCheckout = ?  where idCus = ?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cus.getIdCheckout());
            ps.setInt(2, cus.getIdCus());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public checkIN searchCheckinTime(int idBooking)
    {
        checkIN cin = null;
        String sql = "select * from checkin where idBooking = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idBooking);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cin = new checkIN();
                cin.setCheckinTime(rs.getString("checkinTime"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cin;
    }
    
    public ArrayList<customer> showCustomer()
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select * from customer";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setIdCus(rs.getInt("idCus"));
                cus.setIdCheckin(rs.getInt("idCheckin"));
                cus.setIdBooking(rs.getInt("idBooking"));
                cus.setName(rs.getString("name"));
                cus.setNationality(rs.getString("nationality"));
                cus.setGender(rs.getString("gender"));
                cus.setMobilePhone(rs.getString("mobilePhone"));
                cus.setEmail(rs.getString("email"));
                cus.setIdProof(rs.getString("idProof"));
                cus.setAddress(rs.getString("address"));
                cus.setRoomNo(rs.getString("roomNo"));
                result.add(cus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<customer> searchCustomer1(int idCheckin)
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select * from customer where idCheckin = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCheckin);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setIdCus(rs.getInt("idCus"));
                cus.setIdCheckin(rs.getInt("idCheckin"));
                cus.setIdBooking(rs.getInt("idBooking"));
                cus.setName(rs.getString("name"));
                cus.setNationality(rs.getString("nationality"));
                cus.setGender(rs.getString("gender"));
                cus.setMobilePhone(rs.getString("mobilePhone"));
                cus.setEmail(rs.getString("email"));
                cus.setIdProof(rs.getString("idProof"));
                cus.setAddress(rs.getString("address"));
                cus.setRoomNo(rs.getString("roomNo"));
                result.add(cus);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
