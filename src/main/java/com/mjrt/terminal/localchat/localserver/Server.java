package com.mjrt.terminal.localchat.localserver;

import com.mjrt.terminal.localchat.Connection;
import com.mjrt.terminal.localchat.Messenger;
import com.mjrt.terminal.localchat.util.IpManager;

public class Server extends Messenger {
    private static Server instance;

    private Server() {
    }

    public static Server getInstance() {
        if (instance == null)
            instance = new Server();
        return instance;
    }

    public void createConnection(int port) throws Exception {
        initializeAttributes();
        var serverSocket = Connection.createConnection(port);
        System.out.println("Serving in ip: " + IpManager.getInetAddress() + ", port: " + port + "\n");
        while (true) {
            socket = serverSocket.accept();
            System.out.println("New device connected");
            bindMessageObtainer();
            bindMessageSender();
            System.out.println("User disconnected");
        }
    }
}
