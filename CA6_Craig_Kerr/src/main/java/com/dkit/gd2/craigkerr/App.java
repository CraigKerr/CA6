package com.dkit.gd2.craigkerr;

import com.dkit.gd2.craigkerr.DAO.PlaneDAO.IPlane;
import com.dkit.gd2.craigkerr.DAO.PlaneDAO.MySQLPlaneDAO;
import com.dkit.gd2.craigkerr.DTO.Plane;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Test all implemented methods in MySQLPlane
        Plane p = new Plane(11, "Boeing 747", 4.5f);

        IPlane dao = new MySQLPlaneDAO();
        System.out.println("Find all planes: ");
        System.out.println(dao.findAllPlanes());
        System.out.println("Find plane by id: 1");
        System.out.println(dao.findPlaneById(1));
        System.out.println("Delete plane by id: 2");
        System.out.println(dao.deletePlaneById(2));
        System.out.println("Add plane: ");
        System.out.println(dao.addPlane(p));
        System.out.println(dao.filterPlanesByRating(4));
        System.out.println("Find all planes JSON: ");
        System.out.println(dao.findAllPlanesJSON());
        System.out.println("Find plane by id JSON: 3");
        System.out.println(dao.findPlaneByIdJSON(3));

    }
}
