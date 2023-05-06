package com.dkit.gd2.craigkerr.TCPServer;

import com.dkit.gd2.craigkerr.DAO.MySQLDAO;
import com.dkit.gd2.craigkerr.DAO.PlaneDAO.MySQLPlaneDAO;
import com.dkit.gd2.craigkerr.DTO.Plane;
import com.dkit.gd2.craigkerr.TCPCore.TCPServiceDetails;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ServiceThread extends Thread
{
    private Socket dataSocket;
    private Scanner input;
    private PrintWriter output;
    private int number;

    //Constructor needs:
    //1. A socket to communicate with the client

    public ServiceThread(ThreadGroup group, String name, Socket dataSocket, int number)
    {
        super(group, name);
        try
        {
            this.dataSocket = dataSocket;
            this.number = number;

            //Create stream for reading from the client
            input = new Scanner(new InputStreamReader(this.dataSocket.getInputStream()));
            output = new PrintWriter(this.dataSocket.getOutputStream(), true);

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    //run method. This is called when .start() is called on an instance of teh Thread class. The run method should contain the logic for
    //a) receiving a message from the client
    //b) processing the message
    //c) sending a response to the client
    @Override
    public void run()
    {
        MySQLPlaneDAO dao = new MySQLPlaneDAO();
        String incomingMessage = "";
        String response = "";
        try
        {
            //While the client doesn't want to quit
            while (!incomingMessage.equals(TCPServiceDetails.QUIT_COMMAND))
            {
                //Wipe the response to make sure we don't send old values
                response = null;
                //Read a message from the client
                incomingMessage = input.nextLine();
                System.out.println("Server received: " + incomingMessage);

                //Break up the message into its components
                String[] messageParts = incomingMessage.split(TCPServiceDetails.BREAKING_CHARACTERS);

                //Process the message
                if (messageParts[0].equals(TCPServiceDetails.DISPLAY_ENTITY_BY_ID))
                {
                    System.out.println("Server is processing a display entity by ID request");
                    int id = Integer.parseInt(messageParts[1]);
                    dao.findPlaneById(id);
                    response = "Plane with ID " + messageParts[1] + " is " + dao.findPlaneById(id);
                }
                else if (messageParts[0].equals(TCPServiceDetails.DISPLAY_ALL_ENTITIES))
                {
                    System.out.println("Server is processing a display all entities request");
                    response = "All planes are " + dao.findAllPlanes();
                }
                else if (messageParts[0].equals(TCPServiceDetails.ADD_ENTITY))
                {
                    System.out.println("Server is processing an add entity request");
                    int id = Integer.parseInt(messageParts[1]);
                    String name = messageParts[2];
                    float rating = Float.parseFloat(messageParts[3]);

                    Plane temp = new Plane(id, name, rating);

                    for(int i = 0; i < dao.findAllPlanes().size(); i++)
                    {
                        if (dao.findAllPlanes().get(i).getId() == id)
                        {
                            response = "ID already exists";
                        } else {
                            dao.addPlane(temp);
                            response = "Plane added";
                            break;
                        }

                    }
                }
                else if (incomingMessage.equals(TCPServiceDetails.DELETE_ENTITY))
                {
                    System.out.println("Server is processing a delete entity request");
                    System.out.println("Enter ID of plane to delete: ");
                    Scanner keyboard = new Scanner(System.in);
                    int id = keyboard.nextInt();

                    dao.deletePlaneById(id);

                    response = "Plane deleted";
                }
                else if (incomingMessage.equals(TCPServiceDetails.QUIT_COMMAND))
                {
                    response = TCPServiceDetails.SESSION_TERMINATED;
                }
                else
                {
                    response = TCPServiceDetails.UNRECOGNISED;
                }

                //Send a response to the client
                output.println(response);
                System.out.println("Server sent: " + response);
            }
        }
        catch(Exception e)
        {
            System.out.println(incomingMessage);
            System.out.println("An exception occurred with client number #" + number +": " + e.getMessage());
        }
        finally
        {
            try
            {
                //Shut down the connection
                System.out.println("Closing the connecting with client number #" + number);
                dataSocket.close();
            }
            catch(IOException e)
            {
                System.out.println("Unable to disconnect with client number #" + number);
                System.exit(1);
            }
        }

    }
}
