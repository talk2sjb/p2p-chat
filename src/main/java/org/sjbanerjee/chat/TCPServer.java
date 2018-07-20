package org.sjbanerjee.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
    private ServerSocket socket;

    private TCPServer(String serverAddress, int serverPort) throws Exception {
        this.socket = new ServerSocket(serverPort);
    }

    public static void startServer(String host, int port) throws Exception {
        Runnable server = new TCPServer(host, port);
        Thread thread = new Thread(server);

        thread.start();
        System.out.println("Server started at port " + port);
    }

    private void start() throws Exception {
        String clientSentence;
//        String capitalizedSentence;

        while (true) {
            Socket connectionSocket = this.socket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);

            //Response/ACK
            /*DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            capitalizedSentence = clientSentence.toUpperCase() + 'n';
            outToClient.writeBytes(capitalizedSentence);*/
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (Exception e) {
            System.out.println("Exception starting server!");
            e.printStackTrace();
        }
    }
}
