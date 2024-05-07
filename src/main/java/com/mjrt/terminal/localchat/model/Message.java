package com.mjrt.terminal.localchat.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Message {
    private UUID id;
    private String from;
    private Date sentDate;
    private String message;

    public Message() {
        id = UUID.randomUUID();
    }

    public Message(String from, Date sentDate, String message) {
        this();
        this.from = from;
        this.sentDate = sentDate;
        this.message = message;
    }
}
