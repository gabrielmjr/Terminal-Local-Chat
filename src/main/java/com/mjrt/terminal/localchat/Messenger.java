package com.mjrt.terminal.localchat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjrt.terminal.localchat.model.Message;
import com.mjrt.terminal.localchat.util.DateFormatter;
import lombok.Setter;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Messenger {
    protected Socket socket;
    protected ObjectMapper objectMapper;
    protected Scanner scanner;
    @Setter
    protected String thisUsersNickname;

    protected void initializeAttributes() {
        scanner = Options.getInstance().getScanner();
        objectMapper = Options.getInstance().getObjectMapper();
    }

    protected void bindMessageObtainer() {
            new Thread(() -> {
                try {
                    while (true) {
                        var message = obtainMessage();
                        System.out.printf("%s[%s]: %s%n", message.getFrom(), DateFormatter.fullFormat(message.getSentDate()), message.getMessage());
                    }
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }).start();
    }

    private Message obtainMessage() throws IOException {
        var dataInputStream = new DataInputStream(socket.getInputStream());
        return objectMapper.readValue(dataInputStream.readUTF(), Message.class);
    }

    protected void bindMessageSender() throws IOException {
        while (true) {
            try {
                var messageLabel = scanner.nextLine();
                var message = new Message(
                        thisUsersNickname,
                        new Date(System.currentTimeMillis()),
                        messageLabel
                );
                var dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(objectMapper.writeValueAsString(message));
                dataOutputStream.flush();
                socket.getOutputStream().flush();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
