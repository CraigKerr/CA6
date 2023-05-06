package com.dkit.gd2.craigkerr.DAO.PlaneDAO;

import com.dkit.gd2.craigkerr.Caching.Cache;
import com.dkit.gd2.craigkerr.DTO.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.dkit.gd2.craigkerr.DAO.MySQLDAO;
import com.google.gson.Gson;

public class MySQLPlaneDAO extends MySQLDAO implements IPlane
{
    Cache cache = new Cache();

    @Override
    public List<Plane> findAllPlanes()
    {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        List<Plane> planes = new ArrayList<>();

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM Planes";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("PlaneID");
                String name = rs.getString("PlaneName");
                float rating = rs.getFloat("PlaneRating");
                Plane p = new Plane(id, name, rating);
                planes.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                this.closeConnection(con);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return planes;

    }

    @Override
    public Plane findPlaneById(int id)
    {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        Plane p = null;

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM Planes WHERE PlaneID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                String name = rs.getString("PlaneName");
                float rating = rs.getFloat("PlaneRating");
                p = new Plane(id, name, rating);
            }

            if(p != null)
            {
                cache.put(id, p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                this.closeConnection(con);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return p;
    }

    @Override
    public boolean deletePlaneById(int id)
    {
        Connection con = null;
        PreparedStatement ps;
        boolean deleted = false;

        try
        {
            con = this.getConnection();
            String query = "DELETE FROM Planes WHERE PlaneID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();

            if (rs == 1)
            {
                deleted = true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                this.closeConnection(con);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return deleted;
    }


    @Override
    public boolean addPlane(Plane p)
    {
        Connection con = null;
        PreparedStatement ps;
        int rs;
        boolean added = false;

        try
        {
            con = this.getConnection();
            String query = "INSERT INTO Planes (PlaneName, PlaneRating) VALUES (?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, p.getName());
            ps.setFloat(2, p.getRating());
            rs = ps.executeUpdate();

            if (rs == 1)
            {
                added = true;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                this.closeConnection(con);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return added;
    }

    @Override
    public List<Plane> filterPlanesByRating(float ratingIn)
    {
        //The rating must be greater than or equal to the rating passed in
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        List<Plane> planes = new ArrayList<>();

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM Planes WHERE PlaneRating >= ?";
            ps = con.prepareStatement(query);
            ps.setFloat(1, ratingIn);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("PlaneID");
                String name = rs.getString("PlaneName");
                float rating = rs.getFloat("PlaneRating");
                Plane p = new Plane(id, name, rating);
                planes.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                this.closeConnection(con);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return planes;
    }

    @Override
    public String findAllPlanesJSON()
    {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        List<Plane> planes = new ArrayList<>();
        Gson gson = new Gson();

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM planes";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("PlaneID");
                String name = rs.getString("PlaneName");
                float rating = rs.getFloat("PlaneRating");
                Plane p = new Plane(id, name, rating);
                planes.add(p);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                this.closeConnection(con);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return gson.toJson(planes);
    }

    @Override
    public String findPlaneByIdJSON(int id)
    {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        Plane p = null;
        Gson gson = new Gson();

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM planes WHERE PlaneID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next())
            {
                String name = rs.getString("PlaneName");
                float rating = rs.getFloat("PlaneRating");
                p = new Plane(id, name, rating);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                this.closeConnection(con);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return gson.toJson(p);
    }



    //Create a method which will cache recent queries to planes and return the results from the cache if the query has been run recently
    //This method will be called by the findAllPlanesJSON() and findPlaneByIdJSON() methods
    //The cache will be a Map<String, String> where the key is the query and the value is the JSON string
    //The cache will be cleared every 5 minutes
    //The cache will be cleared if the number of entries in the cache is greater than 100
    //The cache will be cleared if the number of entries in the cache is greater than 10 and the query is not in the cache
    //The cache will be cleared if the number of entries in the cache is greater than 10 and the query is in the cache but the query has not been run in the last 5 minutes


}
