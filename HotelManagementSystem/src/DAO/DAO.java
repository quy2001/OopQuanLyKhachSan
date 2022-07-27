/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author T450s
 */
public class DAO {
    public static Connection con;
    public DAO()
    {
        if(con == null)
        {
            String url = "jdbc:sqlserver://localhost:1433;databasename=HotelManagementSystem";
            String user = "sa";
            String password = "123456";
            
            try
            {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = (Connection) DriverManager.getConnection(url, user, password);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
