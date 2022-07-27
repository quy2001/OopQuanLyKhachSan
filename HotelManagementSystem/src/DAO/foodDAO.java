/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DAO.con;
import Model.DetailFoodOrder;
import Model.FoodOrder;
import Model.customer;
import Model.food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author T450s
 */
public class foodDAO extends DAO{

    public foodDAO() {
        super();
    }
    
    public ArrayList<food> showFood()
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select * from food";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food fd = new food();
                fd.setIdFood(rs.getString("idFood"));
                fd.setFoodName(rs.getString("foodName"));
                fd.setPrice(rs.getInt("price"));
                fd.setStatus(rs.getString("status"));
                result.add(fd);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> showNotFood()
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select * from food where status = 'Available'";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food fd = new food();
                fd.setIdFood(rs.getString("idFood"));
                fd.setFoodName(rs.getString("foodName"));
                fd.setPrice(rs.getInt("price"));
                result.add(fd);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean addFood(food fd)
    {
        String sql = "insert into food values (?, ?, ?, ?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fd.getIdFood());
            ps.setString(2, fd.getFoodName());
            ps.setInt(3, fd.getPrice());
            ps.setString(4, fd.getStatus());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean updateFood(food fd)
    {
        String sql = "update food set foodName = ?, price = ?, status = ? where idFood = ?";
        try 
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fd.getFoodName());
            ps.setInt(2, fd.getPrice());
            ps.setString(4, fd.getIdFood());
            ps.setString(3, fd.getStatus());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean deleteFood(food fd)
    {
        String sql = "delete from food where idFood = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fd.getIdFood());
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    

    public boolean searchFood(food fd)
    {
        boolean result = false;
        String sql = "select * from food where foodName = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fd.getFoodName());
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                fd.setIdFood(rs.getString("idFood"));
                fd.setFoodName(rs.getString("foodName"));
                fd.setPrice(rs.getInt("price"));
                result = true;
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean addFoodOrder(FoodOrder fo)
    {
        int idOrder = 1;
        String sqlAddFoodOrder = "insert into foodOrder values (?, ?, ?, ?, ?)";
        String sql = "select max(idOrder) from foodOrder";
        
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                idOrder = rs.getInt(1);
            }
            idOrder = idOrder + 1;
            
            ps = con.prepareStatement(sqlAddFoodOrder);
            ps.setInt(1, idOrder);
            ps.setInt(2, fo.getIdCus());
            ps.setDate(3, java.sql.Date.valueOf(fo.getOrderDate()));
            ps.setString(4, fo.getOrderTime());
            ps.setInt(5, fo.getTotalPrice());
            ps.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public boolean addDetailFoodOrder(DetailFoodOrder dfo)
    {
        String sqlAddFoodOrder = "insert into detailFoodOrder values (?, ?, ?, ?, ?)";
        
        try
        {
            
            PreparedStatement ps = con.prepareStatement(sqlAddFoodOrder);
            ps.setInt(1, dfo.getIdOrder());
            ps.setString(2, dfo.getIdFood());
            ps.setInt(3, dfo.getQty());
            ps.setInt(4, dfo.getPrice());
            ps.setInt(5, dfo.getAmount());
            ps.executeUpdate();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public FoodOrder searchFoodOrder(Date orderDate)
    {
        FoodOrder fo = null;
        String sql = "select top(1) idOrder from foodOrder where orderDate = ? order by idOrder desc";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, orderDate);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                fo = new FoodOrder();
                fo.setIdOrder(rs.getInt("idOrder"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return fo;
    }
    
    public FoodOrder servicesFees(int idCus)
    {
        FoodOrder fo = null;
        String sql = "select sum(totalPrice) as servicesFees from foodOrder where idCus = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCus);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                fo = new FoodOrder();
                fo.setTotalPrice(rs.getInt("servicesFees"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return fo;
    }
    
    public ArrayList<FoodOrder> searchFoodOrder(int idCus)
    {
        ArrayList<FoodOrder> result = new ArrayList<>();
        String sql = "select * from foodOrder where idCus = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCus);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                FoodOrder fo = new FoodOrder();
                fo.setOrderDate(rs.getDate("orderDate").toLocalDate());
                fo.setOrderTime(rs.getString("orderTime"));
                fo.setTotalPrice(rs.getInt("totalPrice"));
                fo.setIdOrder(rs.getInt("idOrder"));
                result.add(fo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
     
    public ArrayList<DetailFoodOrder> searchDetailFoodOrder(int idCus)
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select a.* from detailFoodOrder a inner join foodOrder b on a.idOrder = b.idOrder where b.idCus = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCus);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setIdFood(rs.getString("idFood"));
                dfo.setPrice(rs.getInt("price"));
                dfo.setQty(rs.getInt("qty"));
                dfo.setAmount(rs.getInt("amount"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<DetailFoodOrder> searchDetailFoodOrder(int idCus, int idOrder)
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select a.* from detailFoodOrder a inner join foodOrder b on a.idOrder = b.idOrder where b.idCus = ? and b.idOrder = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCus);
            ps.setInt(2, idOrder);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setIdFood(rs.getString("idFood"));
                dfo.setPrice(rs.getInt("price"));
                dfo.setQty(rs.getInt("qty"));
                dfo.setAmount(rs.getInt("amount"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public food searchFood(String idFood)
    {
        food f = null;
        String sql = "select foodName from food where idFood = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idFood);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                f = new food();
                f.setFoodName(rs.getString("foodName"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return f;
    }
    
    public ArrayList<DetailFoodOrder> detailfoodStatistic()
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select sum(qty) as orderQuantity from detailFoodOrder where idFood in (select idFood from food) group by idFood";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setQty(rs.getInt("orderQuantity"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> showFood(Date sDate, Date eDate)
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select distinct a.* from food a, foodOrder b, detailFoodOrder c where a.idFood = c.idFood and b.idOrder = c.idOrder and orderDate >= ? and orderDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food fd = new food();
                fd.setIdFood(rs.getString("idFood"));
                fd.setFoodName(rs.getString("foodName"));
                fd.setPrice(rs.getInt("price"));
                result.add(fd);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<DetailFoodOrder> detailfoodStatistic(Date sDate, Date eDate)
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select sum(qty) as orderQuantity from detailFoodOrder a, foodOrder b where a.idOrder = b.idOrder and idFood in (select idFood from food) and orderDate >= ? and orderDate <= ?  group by idFood";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setQty(rs.getInt("orderQuantity"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<customer> showCusDetailOrder()
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select a.name, a.roomNO from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setName(rs.getString("name"));
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
    
    public ArrayList<FoodOrder> showFoodOrderDetailOrder()
    {
        ArrayList<FoodOrder> result = new ArrayList<>();
        String sql = "select c.orderDate from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                FoodOrder fo = new FoodOrder();
                fo.setOrderDate(rs.getDate("orderDate").toLocalDate());
                result.add(fo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> showFoodDetailOrder()
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select b.foodName from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food f = new food();
                f.setFoodName(rs.getString("foodName"));
                result.add(f);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<DetailFoodOrder> showDetailFoodOrderDetailOrder()
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select d.idOrder, d.qty, d.price, d.amount from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setIdOrder(rs.getInt("idOrder"));
                dfo.setQty(rs.getInt("qty"));
                dfo.setPrice(rs.getInt("price"));
                dfo.setAmount(rs.getInt("amount"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<customer> showCusDetailOrder(Date sDate, Date eDate)
    {
        ArrayList<customer> result = new ArrayList<>();
        String sql = "select a.name, a.roomNO from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder and orderDate >= ? and orderDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                customer cus = new customer();
                cus.setName(rs.getString("name"));
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
    
    public ArrayList<FoodOrder> showFoodOrderDetailOrder(Date sDate, Date eDate)
    {
        ArrayList<FoodOrder> result = new ArrayList<>();
        String sql = "select c.orderDate from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder and orderDate >= ? and orderDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                FoodOrder fo = new FoodOrder();
                fo.setOrderDate(rs.getDate("orderDate").toLocalDate());
                result.add(fo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> showFoodDetailOrder(Date sDate, Date eDate)
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select b.foodName from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder and orderDate >= ? and orderDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food f = new food();
                f.setFoodName(rs.getString("foodName"));
                result.add(f);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<DetailFoodOrder> showDetailFoodOrderDetailOrder(Date sDate, Date eDate)
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select d.idOrder, d.qty, d.price, d.amount from food b, customer a, foodOrder c, detailFoodOrder d where a.idCus = c.idCus and b.idFood = d.idFood and d.idOrder = c.idOrder and orderDate >= ? and orderDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setIdOrder(rs.getInt("idOrder"));
                dfo.setQty(rs.getInt("qty"));
                dfo.setPrice(rs.getInt("price"));
                dfo.setAmount(rs.getInt("amount"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> detailfoodStatistic2()
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select foodName from food where idFood not in (select idFood from detailFoodOrder)";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food f = new food();
                f.setFoodName(rs.getString("foodName"));
                result.add(f);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> showFood1()
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select distinct a.* from food a, detailFoodOrder b where a.idFood = b.idFood";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food fd = new food();
                fd.setIdFood(rs.getString("idFood"));
                fd.setFoodName(rs.getString("foodName"));
                fd.setPrice(rs.getInt("price"));
                result.add(fd);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<DetailFoodOrder> detailfoodStatistic1()
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select sum(qty) as orderQuantity from detailFoodOrder where idFood in (select distinct a.idFood from food a, detailFoodOrder b where a.idFood = b.idFood) group by idFood";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setQty(rs.getInt("orderQuantity"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> showFood1(Date sDate, Date eDate)
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select distinct a.* from food a, foodOrder b, detailFoodOrder c where a.idFood = c.idFood and b.idOrder = c.idOrder and orderDate >= ? and orderDate <= ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food fd = new food();
                fd.setIdFood(rs.getString("idFood"));
                fd.setFoodName(rs.getString("foodName"));
                fd.setPrice(rs.getInt("price"));
                result.add(fd);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<DetailFoodOrder> detailfoodStatistic1(Date sDate, Date eDate)
    {
        ArrayList<DetailFoodOrder> result = new ArrayList<>();
        String sql = "select sum(qty) as orderQuantity from detailFoodOrder a, foodOrder b where a.idOrder = b.idOrder and idFood in (select distinct a.idFood from food a, detailFoodOrder b where a.idFood = b.idFood) and orderDate >= ? and orderDate <= ?  group by idFood";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                DetailFoodOrder dfo = new DetailFoodOrder();
                dfo.setQty(rs.getInt("orderQuantity"));
                result.add(dfo);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> detailfoodStatistic2(Date sDate, Date eDate)
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select foodName from food where idFood not in (select idFood from detailFoodOrder a, foodOrder b where a.idOrder = b.idOrder and orderDate >= ? and orderDate <= ?)";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, sDate);
            ps.setDate(2, eDate);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food f = new food();
                f.setFoodName(rs.getString("foodName"));
                result.add(f);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public ArrayList<food> showFood(String foodName)
    {
        ArrayList<food> result = new ArrayList<>();
        String sql = "select * from food where foodName = ?";
        try
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, foodName);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                food fd = new food();
                fd.setIdFood(rs.getString("idFood"));
                fd.setFoodName(rs.getString("foodName"));
                fd.setPrice(rs.getInt("price"));
                fd.setStatus(rs.getString("status"));
                result.add(fd);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

}
