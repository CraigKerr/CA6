package com.dkit.gd2.craigkerr.DAO.PlaneDAO;

import com.dkit.gd2.craigkerr.DTO.Plane;

import java.util.List;

public interface IPlane
{
    List<Plane> findAllPlanes();
    Plane findPlaneById(int id);
    boolean deletePlaneById(int id);
    boolean addPlane(Plane p);
    List<Plane> filterPlanesByRating(float rating);
    String findAllPlanesJSON();
    String findPlaneByIdJSON(int id);

}
