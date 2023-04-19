package com.dkit.gd2.craigkerr.DTO;

public class Plane
{
    private int id;
    private String name;
    private float rating;

    public Plane(int id, String name, float rating)
    {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public float getRating()
    {
        return this.rating;
    }

    @Override
    public String toString()
    {
        return "Plane{" + "id=" + id + ", name=" + name + ", rating=" + rating + '}';
    }

}
