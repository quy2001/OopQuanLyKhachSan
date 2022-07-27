/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.users;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author T450s
 */
public class userDAO extends DAO{

    public userDAO() {
        super();
    }
    
    public boolean checkLogin(users user)
    {
        boolean result = false;
        String sql = "select * from users where email = ? and password = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
//                user.setName(rs.getString("name"));
//                user.setSecurityQuestion(rs.getString("securityPassword"));
//                user.setAnswer(rs.getString("answer"));
//                user.setAddress(rs.getString("address"));
                user.setStatus(rs.getString("status"));
                result = true;
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean addUser(users user)
    {
        String sql = "insert into users values (?, ?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getSecurityQuestion());
            ps.setString(5, user.getAnswer());
            ps.setString(6, user.getAddress());
            ps.setString(7, "false");
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public users searchUser(String email)
    {
        users user = null;
        String sql = "select * from users where email = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                user = new users();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));
                user.setAddress(rs.getString("address"));
                user.setStatus(rs.getString("status"));
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
        return user;
    }
    
    public boolean updateUserPassword(users user)
    {
        String sql = "update users set password = ? where email = ?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getEmail());
            //ps.setString(3, user.getSecurityQuestion());
            //ps.setString(3, user.getAnswer());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ArrayList<users> showUsers()
    {
        ArrayList<users> result = new ArrayList<>();
        String sql = "select * from users";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                users user = new users();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));
                user.setAddress(rs.getString("address"));
                user.setStatus(rs.getString("status"));
                result.add(user);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean updateUserStartus(users user)
    {
        String sql = "update users set status = ? where email = ?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getStatus());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
