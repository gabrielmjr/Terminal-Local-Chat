package com.mjrt.terminal.localchat.localserver;

import com.mjrt.terminal.localchat.Connection;
import com.mjrt.terminal.localchat.Messenger;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Path;

import static com.mjrt.terminal.localchat.Options.printMessageL;

public class Server extends Messenger {
    private static Server instance;

    private Server() {}

    public void createConnection(int port) throws IOException {
        initializeAttributes();
        socket = new ServerSocket(port).accept();
        printMessageL("Serving in ip: " + socket.getInetAddress() + ", port: " + socket.getPort() + "\n");
        socket = Connection.createConnection(port);
        bindMessageObtainer();
        bindMessageSender();
    }

    public static Server getInstance() {
        if (instance == null)
            instance = new Server();
        return instance;
    }
}
