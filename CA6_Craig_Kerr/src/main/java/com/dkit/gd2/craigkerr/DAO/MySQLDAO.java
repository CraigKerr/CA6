package com.dkit.gd2.craigkerr.DAO;

import com.dkit.gd2.craigkerr.Exceptions.DAOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class MySQLDAO
{
    public Connection getConnection() throws DAOException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ca6";
        String username = "root";
        String password = System.getenv("MYSQL_PASSWORD");
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found " +e.getMessage());
            System.exit(1);
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed " +e.getMessage());
        }
        System.out.println("Connection Successful");
        return con;
    }

    public void closeConnection(Connection con) throws DAOException
    {
        try
        {
            if (con != null)
            {
                con.close();
                con = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to close the Connection"+e.getMessage());
            System.exit(1);
        }
    }
}
