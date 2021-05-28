package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void connect(){
        try {
            System.out.print("Connect as client\n");
            Socket clientSocket = new Socket ("localhost",50001);
            InputStream is = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String receivedData = br.readLine();
            System.out.println("Received Data: "+receivedData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
