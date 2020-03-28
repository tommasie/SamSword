/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collerton.samuraisword.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tommasie
 */
public class Server extends Thread {

    private String host;
    private int port;
    private Room room;
    private static final int MAX_CONNECTIONS = 7;

    ServerSocket socket;

    /**
     * This executor service has MAX_CONNECTIONS threads.
     * So it means your server can process max MAX_CONNECTIONS concurrent requests.
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONNECTIONS);

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
        room = new Room();
        initSocket();
    }

    private void initSocket() {
        try {
            this.socket = new ServerSocket(port);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket clientSocket = socket.accept();
                System.out.println("Reading request");
                executorService.submit(new ServiceRequest(clientSocket, room));
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class ServiceRequest implements Runnable {

    private final Socket socket;
    private Room room;
    private final MessageInterface iface;

    public ServiceRequest(Socket connection, Room room) {
        this.socket = connection;
        this.room = room;
        this.iface = new MessageInterface(this);
    }

    @Override
    public void run() {

        //Do your logic here. You have the `socket` available to read/write data.
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while(!(line = reader.readLine()).equals("quit")) {
                iface.receive(line);
            }
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
        //Make sure to close
        try {
            socket.close();
        }catch(IOException ioe) {
            System.out.println("Error closing client connection");
        }
    }

    public void runByteStream() {
        //byte[] bytes = IOUtils.toByteArray(socket.getInputStream());
    }

    public void writeOnStream(String str) {
        try {
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.print(str);
        writer.flush();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}