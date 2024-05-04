package com.mjrt.terminal.localchat;

import java.util.Scanner;

public class TerminalChat {
    private Options options;

    public static void main(String[] args) {
        new TerminalChat().run();
    }

    private void run() {
        initializeAttributes();
        while (true) {
            options.process();
        }
    }

    private void initializeAttributes() {
        options = Options.getInstance();
    }
}