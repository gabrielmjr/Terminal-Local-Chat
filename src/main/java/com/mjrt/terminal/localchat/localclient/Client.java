package com.mjrt.terminal.localchat.localclient;

import com.mjrt.terminal.localchat.Connection;
import com.mjrt.terminal.localchat.Messenger;

import static com.mjrt.terminal.localchat.Options.printMessageL;

public class Client extends Messenger {
    private static Client instance;

    private Client() {}

    public void connect(String ipAddress, int port) throws Exception {
        initializeAttributes();
        socket = Connection.connectAsClient(ipAddress, port);
        printMessageL("Connected");
        bindMessageObtainer();
    }

    public static Client getInstance() {
        if (instance == null)
            instance = new Client();
        return instance;
    }
}