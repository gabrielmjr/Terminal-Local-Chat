package com.mjrt.terminal.localchat.localclient;

import com.mjrt.terminal.localchat.Options;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Handler;

import static com.mjrt.terminal.localchat.Options.printMessageL;

public class Client {
    private static Client instance;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private Socket socket;
    private PrintWriter printWriter;
    private Scanner scanner;

    private Client() {
        scanner = Options.getInstance().getScanner();
    }

    public void connect(String ipAddress, int port) throws Exception {
        socket = new Socket(ipAddress, port);
        printMessageL("Connected");
        new Thread(() -> {
            String line;
            while (true) {
                try {
                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    if ((line = bufferedReader.readLine()) != null)
                        System.out.println(line);
                    Thread.sleep(600);
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        while (true) {
            System.out.print(">>> ");
            var message = scanner.nextLine();
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(message);
            printWriter.flush();
        }
    }

    public static Client getInstance() {
        if (instance == null)
            instance = new Client();
        return instance;
    }
}
