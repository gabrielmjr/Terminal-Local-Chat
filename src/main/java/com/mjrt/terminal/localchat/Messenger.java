package com.mjrt.terminal.localchat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjrt.terminal.localchat.model.Message;
import com.mjrt.terminal.localchat.util.DateFormatter;
import lombok.Setter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import static com.mjrt.terminal.localchat.util.ThreadUtils.threadPools;

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
        threadPools.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Message message = null;
                try {
                    message = obtainMessage();
                    System.out.println(message.getFrom() + "[" + DateFormatter.fullFormat(message.getSentDate()) + "]: " + message.getMessage());
                } catch (Exception e) {
                    System.err.println("Device disconnected");
                    System.exit(0);
                }
            }
        });
    }

    private Message obtainMessage() throws Exception {
        var dataInputStream = new DataInputStream(socket.getInputStream());
        return objectMapper.readValue(dataInputStream.readUTF(), Message.class);
    }

    protected void bindMessageSender() {
        try {
            while (true) {
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
            }
        } catch (IOException e) {
            System.err.println("Error when sending message: " + e.getMessage());
        }
    }
}
