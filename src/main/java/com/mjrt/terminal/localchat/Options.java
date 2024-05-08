package com.mjrt.terminal.localchat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjrt.terminal.localchat.localclient.Client;
import com.mjrt.terminal.localchat.localserver.Server;
import lombok.Getter;

import java.util.Scanner;

public class Options {
    private static Options instance;
    private final Client client;
    private final Server server;
    @Getter
    private final Scanner scanner;
    @Getter
    private final ObjectMapper objectMapper;
    private int port;
    private String nickname;

    private Options() {
        scanner = new Scanner(System.in);
        client = Client.getInstance();
        server = Server.getInstance();
        objectMapper = new ObjectMapper();
    }

    public void process() {
        printMessageL("To use the chat your devices must have wifi.");
        printMessage("Enter your username/nickname: ");
        nickname = scanner.nextLine();
        printMessageL("\nType 1 to be the server (Turn on hotspot).");
        printMessageL("Type 2 to be the client (Turn on wifi).");
        System.out.print(">>> ");
        switch(readInt()) {
            case 1:
                startAsServer();
                break;
            case 2:
                startAsClient();
                break;
        }
    }

    private void startAsServer() {
        printMessage("Enter the port to listen: ");
        port = readInt();
        try {
            server.setThisUsersNickname(nickname);
            server.createConnection(port);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void startAsClient() {
        printMessage("Enter the ip address: ");
        String ipAddress = readString();
        printMessage("Enter the listened port: ");
        port = readInt();
        try {
            client.setThisUsersNickname(nickname);
            client.connect(ipAddress, port);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void printMessage(String message) {
        System.out.print(message);
    }

    public static  void printMessageL(String message) {
        System.out.println(message);
    }

    public String readString() {
        return scanner.next();
    }

    private int readInt() {
        return scanner.nextInt();
    }

    public static Options getInstance() {
        if (instance == null)
            instance = new Options();
        return instance;
    }
}
