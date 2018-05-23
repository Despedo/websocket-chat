package com.websocket.model;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public void addTime(){
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        this.content = time + ": " + content;
    }

    public void setPrivate(){
        this.content = "PRIVATE -> " + content;
    }
}