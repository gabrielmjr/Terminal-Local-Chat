package com.mjrt.terminal.localchat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private UUID id;
    private String from;
    private Date sentDate;
    private Message message;
}
