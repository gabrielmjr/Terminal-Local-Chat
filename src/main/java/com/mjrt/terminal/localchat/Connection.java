package com.mjrt.terminal.localchat;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    @Contract("_, _ -> new")
    public static @NotNull Socket connectAsClient(String ip, int port) throws IOException {
        return new Socket(ip, port);
    }

    @Contract("_ -> new")
    public static @NotNull ServerSocket createConnection(int port) throws IOException {
        return new ServerSocket(port);
    }
}
