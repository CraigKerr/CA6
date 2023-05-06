package com.dkit.gd2.craigkerr.TCPServer;

import com.dkit.gd2.craigkerr.TCPCore.TCPServiceDetails;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class StreamServer
{
    public static void main(String[] args)
    {
        try
        {
            //Set up a connection socket to listen for connections
            ServerSocket listeningSocket = new ServerSocket(8080);

            //Set up a thread group to store all of the client threads together
            ThreadGroup clientThreads = new ThreadGroup("Client Threads");
            //Place more emphasis on accepting clients rather than processing
            clientThreads.setMaxPriority(Thread.currentThread().getPriority() - 1);

            boolean continueRunning = true;
            int numberClients = 0;

            while(continueRunning)
            {
                System.out.println("Server is up and listening for connections....");
                //Wait for an incoming connection and build a communications link
                Socket dataSocket = listeningSocket.accept();

                numberClients++;
                System.out.println("Server has now accepted " + numberClients + " clients");

                //Build the thread to handle the client
                //Thread needs:
                //1. A group to be stored in
                //2. A name to listed under
                //3. A socket to communicate through
                //4. Maybe other stuff
                ServiceThread newClient = new ServiceThread(clientThreads, dataSocket.getInetAddress() + "", dataSocket, numberClients);
                newClient.start();


            }
            listeningSocket.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
