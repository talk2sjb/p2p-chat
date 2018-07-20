package org.sjbanerjee.chat;

import java.io.PrintWriter;
import java.net.Socket;

class TCPClient implements Runnable {
    private Socket socket;
    private String message;

    private TCPClient(String serverAddress, int serverPort, String message) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.message = message;
    }

    public static void startClient(String host, int port, String message) throws Exception {
        TCPClient client = new TCPClient(host, port, message);
        Thread thread = new Thread(client);

        thread.start();
//        System.out.println("Client Started and connected to " + client.socket.getInetAddress());
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(this.message);
            out.flush();
            this.socket.close();
        } catch (Exception e) {
            System.out.println("Couldn't start client! ");
            e.printStackTrace();
        }
    }
}
