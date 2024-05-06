package com.mjrt.terminal.localchat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjrt.terminal.localchat.model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Messenger {
    protected Socket socket;
    protected ObjectMapper objectMapper;
    protected Scanner scanner;

    protected void initializeAttributes() {
        scanner = Options.getInstance().getScanner();
        objectMapper = Options.getInstance().getObjectMapper();
    }

    protected void bindMessageObtainer() {
        new Thread(() -> {
            while (true) {
                try {
                    var message = obtainMessage();
                    System.out.println(message.getFrom() + ": " + message.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private Message obtainMessage() throws IOException {
        StringBuilder lines = new StringBuilder();
        String line;
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((line = bufferedReader.readLine()) != null)
            lines.append(line);
        return objectMapper.readValue(lines.toString(), Message.class);
    }

    protected void bindMessageSender() throws IOException {
        while (true) {
            var message = scanner.nextLine();
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println(message);
            printWriter.flush();
        }
    }
}
