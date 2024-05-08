package com.mjrt.terminal.localchat.localserver;

import com.mjrt.terminal.localchat.Connection;
import com.mjrt.terminal.localchat.Messenger;
import com.mjrt.terminal.localchat.util.IpManager;

import java.io.IOException;

import static com.mjrt.terminal.localchat.Options.printMessageL;

public class Server extends Messenger {
    private static Server instance;

    private Server() {}

    public void createConnection(int port) throws IOException {
        initializeAttributes();
        var serverSocket = Connection.createConnection(port);
        printMessageL("Serving in ip: " + IpManager.getInetAddress() + ", port: " + port + "\n");
        socket = serverSocket.accept();
        bindMessageObtainer();
        bindMessageSender();
    }

    public static Server getInstance() {
        if (instance == null)
            instance = new Server();
        return instance;
    }
}
