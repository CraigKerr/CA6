package com.dkit.gd2.craigkerr.TCPClient;

import com.dkit.gd2.craigkerr.TCPCore.TCPServiceDetails;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StreamClient
{
    public static void main(String[] args)
    {
        try
        {
            //Step 1: Establish a channel of communication
            Socket dataSocket = new Socket("localhost", 8080);

            //Step 2: Build input and output streams
            OutputStream out = dataSocket.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));

            Scanner keyboard = new Scanner(System.in);
            String message = "";
            while(!message.equals(TCPServiceDetails.QUIT_COMMAND))
            {
                displayMenu();
                int choice = getNumber(keyboard);
                String response = "";
                if(choice >= 0 && choice < 4)
                {
                    switch(choice)
                    {
                        case 0:
                            message = TCPServiceDetails.QUIT_COMMAND;

                            //Send message
                            output.println(message);
                            output.flush();

                            response = input.nextLine();
                            if(response.equals(TCPServiceDetails.SESSION_TERMINATED))
                            {
                                System.out.println("Session ended");
                            }
                            break;
                        case 1:
                            message = reqDisplayByID(keyboard);

                            //Send message to server
                            output.println(message);
                            output.flush();

                            //Get response from server

                            response = input.nextLine();
                            System.out.println("Response from server: " + response);
                            break;
                        case 2:
                            message = TCPServiceDetails.DISPLAY_ALL_ENTITIES;
                            //Send message to server
                            output.println(message);
                            output.flush();

                            //Get response from server

                            response = input.nextLine();
                            System.out.println("Displaying All planes: " + response);
                            break;
                        case 3:
                            message = reqAdd(keyboard);

                            //Send message to server
                            output.println(message);
                            output.flush();

                            //Get response from server

                            response = input.nextLine();
                            System.out.println("The server has added: " + response);
                            break;
                        case 4:
                            message = reqDelete(keyboard);

                            //Send message to server
                            output.println(message);
                            output.flush();

                            //Get response from server

                            response = input.nextLine();
                            System.out.println("The server has deleted: " + response);
                            break;
                    }
                    if(response.equals(TCPServiceDetails.UNRECOGNISED))
                    {
                        System.out.println("Sorry, the server does not recognise this command");
                    }
                }
                else
                {
                    System.out.println("Please select and option from the menu");
                }
            }
            System.out.println("Thank you for using the Stream based Combo Service");
            dataSocket.close();
        }
        catch(UnknownHostException e)
        {
            System.out.println(e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static int getNumber(Scanner keyboard)
    {
        boolean numberEntered = false;
        int number = 0;
        while(!numberEntered)
        {
            try
            {
                number = keyboard.nextInt();
                numberEntered = true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Please enter a number");
                keyboard.nextLine();
            }
        }
        keyboard.nextLine();
        return number;
    }

    private static String reqDisplayByID(Scanner keyboard)
    {
        StringBuffer message = new StringBuffer(TCPServiceDetails.DISPLAY_ENTITY_BY_ID);
        message.append(TCPServiceDetails.BREAKING_CHARACTERS);
        //Enter ID
        System.out.println("Enter an ID");
        String id = keyboard.nextLine();
        message.append(id);
        return message.toString();
    }

    private static String reqDisplayAll(Scanner keyboard)
    {
        StringBuffer message = new StringBuffer(TCPServiceDetails.DISPLAY_ALL_ENTITIES);
        message.append(TCPServiceDetails.BREAKING_CHARACTERS);
        return message.toString();
    }

    private static String reqAdd(Scanner keyboard)
    {
        StringBuffer message = new StringBuffer(TCPServiceDetails.ADD_ENTITY);
        message.append(TCPServiceDetails.BREAKING_CHARACTERS);
        //Enter Details
        System.out.println("Enter an ID");
        int id = keyboard.nextInt();

        System.out.println("Enter a name");
        String name = keyboard.next();

        System.out.println("Enter a rating");
        float rating = keyboard.nextFloat();

        String tempString = id + TCPServiceDetails.BREAKING_CHARACTERS + name + TCPServiceDetails.BREAKING_CHARACTERS + rating;
        message.append(tempString);
        return message.toString();
    }

    private static String reqDelete(Scanner keyboard)
    {
        StringBuffer message = new StringBuffer(TCPServiceDetails.DELETE_ENTITY);
        message.append(TCPServiceDetails.BREAKING_CHARACTERS);
        return message.toString();
    }

    private static void displayMenu()
    {
        System.out.println("Please select an option from the menu");
        System.out.println("0. Quit");
        System.out.println("1. Display by ID");
        System.out.println("2. Display all");
        System.out.println("3. Add new entity");
        System.out.println("4. Delete entity");
    }
}
