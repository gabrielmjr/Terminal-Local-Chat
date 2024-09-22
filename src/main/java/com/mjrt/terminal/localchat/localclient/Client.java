package com.mjrt.terminal.localchat.localclient;

import com.mjrt.terminal.localchat.Connection;
import com.mjrt.terminal.localchat.Messenger;

public class Client extends Messenger {
    private static Client instance;

    private Client() {
    }

    public static Client getInstance() {
        if (instance == null)
            instance = new Client();
        return instance;
    }

    public void connect(String ipAddress, int port) throws Exception {
        initializeAttributes();
        socket = Connection.connectAsClient(ipAddress, port);
        System.out.println("Connected");
        bindMessageObtainer();
        bindMessageSender();
        System.out.println("Disconnected");
    }
}