package com.dkit.gd2.craigkerr.Exceptions;

import java.sql.SQLException;

public class DAOException extends SQLException
{
    public DAOException()
    {
        super();
    }

    public DAOException(String message)
    {
        super(message);
    }


}
