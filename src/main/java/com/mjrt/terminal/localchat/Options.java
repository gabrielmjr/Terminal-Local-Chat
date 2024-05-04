package com.mjrt.terminal.localchat;

import com.mjrt.terminal.localchat.localclient.Client;

import java.lang.invoke.StringConcatException;
import java.util.Scanner;

public class Options {
    private static Options instance;
    private Client client;
    private final Scanner scanner;
    private String ipAddress;
    private int port;

    private Options() {
        scanner = new Scanner(System.in);
    }

    public void process() {
        client = Client.getInstance();
        printMessageL("To use the chat your devices must have wifi.");
        printMessageL("Type 1 to be the server (Turn on hotspot).");
        printMessageL("Type 2 to be the client (Turn on wifi).");
        System.out.print(">>> ");
        switch(readInt()) {
            case 1:
                printMessageL("Enter the port to listen: ");
                break;
            case 2:
                startAsServer();
                break;
        }
    }

    private void startAsServer() {
        printMessage("Enter the ip address: ");
        ipAddress = readString();
        printMessage("Enter the listened port: ");
        port = readInt();
        try {
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

    public Scanner getScanner() {
        return scanner;
    }

    public static Options getInstance() {
        if (instance == null)
            instance = new Options();
        return instance;
    }
}
