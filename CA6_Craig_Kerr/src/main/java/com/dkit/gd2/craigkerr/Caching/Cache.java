package com.dkit.gd2.craigkerr.Caching;

import com.dkit.gd2.craigkerr.DTO.Plane;

import java.util.Queue;

public class Cache
{
    //Fields
    private Queue<CacheItem> cache;
    private int maxSize;

    //Constructor
    public Cache()
    {
        cache = null;
        this.maxSize = 3;
    }

    //Methods
    public int getMaxSize()
    {
        return maxSize;
    }

    public void viewCache()
    {
        for(CacheItem item : cache)
        {
            System.out.println(item);
        }
    }

    public void addToCache(CacheItem item)
    {
        if(cache.size() < maxSize)
        {
            cache.add(item);
        }
        else
        {
            cache.remove();
            cache.add(item);
        }
    }

    //Remove the oldest item in the cache
    public void removeFromCache()
    {
        cache.remove();
    }

    public void clearCache()
    {
        cache.clear();
    }

    public void put(int id, Plane p)
    {
    }
}


