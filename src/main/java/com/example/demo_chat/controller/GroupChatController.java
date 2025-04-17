package com.example.demo_chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GroupChatController {
    // sử dụng chat nhóm
    @MessageMapping("/group-chat") // Mapping từ client gửi tới /app/message
    @SendTo("/topic/messages") // Gửi tin nhắn tới các client subscribe /topic/messages
    public String handleMessage(String message) {
        System.out.println("--------------"+message);
        return "Server nhận: " + message;
    }
}
