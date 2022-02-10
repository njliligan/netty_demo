package com.njganlili.nettydemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class BIOTest extends Thread{
    public void run(){
        for (;;){
            try {
                InetAddress address  = Inet4Address.getByAddress(new byte[]{127,0,0,1});
                ServerSocket serverSocket = new ServerSocket(8080,50,address);
                System.out.println("wait connect.....");
                Socket socket = serverSocket.accept();
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.write("hello , this is response".getBytes(StandardCharsets.UTF_8));
                dataOutputStream.flush();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)  {
        new BIOTest().run();
    }
}
