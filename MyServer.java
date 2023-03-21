package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class MyServer {
    private int port;
    private volatile boolean stop;
    ClientHandler clientHandler;

    public MyServer(int port, ClientHandler clientHandler) {
        this.port = port;
        this.clientHandler = clientHandler;
        stop = false;
    }

    public void start() {
        new Thread(this::runServer).start();
    }

    private void runServer() {
        try {
            ServerSocket server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while (!stop) {
                try {
                    Socket client = server.accept();
                    clientHandler.handleClient(client.getInputStream(), client.getOutputStream());
                    client.close();
                } catch (SocketTimeoutException e) {
                }
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stop = true;
    }
}