package com.dkit.gd2.craigkerr.DAO.PlaneDAO;

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

}
