package com.websocket.controller;

import com.websocket.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/message")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessage.addTime();
        return chatMessage;
    }

    @MessageMapping("/chat/private.{username}")
    public void filterPrivateMessage(@Payload ChatMessage chatMessage, @DestinationVariable("username") String username) {
        chatMessage.setPrivate();
        chatMessage.addTime();
        // send private message to the recipient
        simpMessagingTemplate.convertAndSend("/topic/private." + username, chatMessage);
        // send private message to the sender
        simpMessagingTemplate.convertAndSend("/topic/private." + chatMessage.getSender(), chatMessage);
    }

    @MessageMapping("/chat/user")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}