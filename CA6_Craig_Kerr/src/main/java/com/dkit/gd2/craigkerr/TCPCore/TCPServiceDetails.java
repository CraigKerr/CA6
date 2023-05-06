package com.dkit.gd2.craigkerr.TCPCore;

public class TCPServiceDetails
{
    //Create variables that hold information on how this protocol works
    //By providing this information in variables, you can change it easily
    //and you need to change it here, not on both the client and server

    public static final int SERVER_PORT = 50007;
    public static final String BREAKING_CHARACTERS = "%%";
    //Command strings
    public static final String DISPLAY_ENTITY_BY_ID = "DISPLAY.ID";
    public static final String DISPLAY_ALL_ENTITIES = "DISPLAY.ALL";
    public static final String ADD_ENTITY = "ADD";
    public static final String DELETE_ENTITY = "DELETE";
    public static final String QUIT_COMMAND = "QUIT";

    //Response strings
    public static final String UNRECOGNISED = "UNKNOWN COMMAND";
    public static final String SESSION_TERMINATED = "GOODBYE";
}
