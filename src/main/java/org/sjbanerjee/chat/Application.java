package org.sjbanerjee.chat;

import java.util.Scanner;

public class Application {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;
    private static final int CLIENT_PORT = 8080;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        try {
            TCPServer.startServer(SERVER_HOST, SERVER_PORT);
            while(scanner.hasNext()) {
                TCPClient.startClient(SERVER_HOST, CLIENT_PORT, scanner.nextLine());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
