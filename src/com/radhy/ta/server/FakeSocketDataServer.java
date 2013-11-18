/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.radhy.ta.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author zakyalvan
 */
public class FakeSocketDataServer {
    private static final Logger LOGGER = Logger.getLogger("DummyServerExample");
    private static ServerSocket serverSocket;
	
    /**
    * @param args
    */
    public static void main(String[] args) throws Exception {
        try {
            serverSocket = new ServerSocket(3333); 
            while(true) {
                handleClient();
            }
	}
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void handleClient() throws Exception {
        LOGGER.info("Waiting for client request.");
        Socket socket = null;
        Scanner input = null;
        PrintWriter output = null;
		
        try {
            socket = serverSocket.accept();
			
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);
            
//            StringBuilder contentBuilder = new StringBuilder();
//            while(input.hasNextLine()) {
//                String newLine = input.nextLine();
//                contentBuilder.append(newLine);
//                contentBuilder.append("\n");
//                LOGGER.info(newLine);
//            }
//            LOGGER.info(contentBuilder.toString());
            
            for(int i = 0; i < 20000; i++) {
                Thread.sleep(2);
                
                Double max = 2000d;
                Double min = -2000d;
                
                Double x = min + Math.random() * (max - min);
                Double y = min + Math.random() * (max - min);
                Double z = min + Math.random() * (max - min);
                
                output.println(x + " " + y + " " + z);
            }
            output.println("STOP");
        }
        finally {
            if(socket != null) {
                socket.close();
                input.close();
                output.close();
            }
	}
    }
}
