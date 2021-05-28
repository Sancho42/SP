package com.company;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int SERVER_PORT = 50001;

    public static void start (){

        try {
            System.out.print("Start server\n");
            ServerSocket server = new ServerSocket(SERVER_PORT);
            Socket clientConn = server.accept();
            System.out.print("Client connected\n");
            DataOutputStream serverOutput = new DataOutputStream(clientConn.getOutputStream());
            serverOutput.writeBytes("Hello from server\n");
            clientConn.close();
            System.out.print("Client disconnected\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
