package com.example.demo_chat.controller;

import com.example.demo_chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin("*")
//@RequestMapping ("/chat")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
    // show form chat

    @GetMapping("/form-chat")
    public String showFormChat(Model model, Authentication authentication) {
        String username = authentication.getName();

        // Lấy tất cả các roles (authorities)
//        String roles = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(", ")); // ví dụ: "ROLE_USER, ROLE_ADMIN"
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN")); // kiểm tra
        model.addAttribute("username", username);
        System.out.println("-----------"+ isAdmin);
        // kiểm tra role để trả 2 màn hình khác nhau
        // nếu admin trả về màn hình admin
        if (isAdmin) {
            return "chat/chatAdmin"; // trả về view cho admin
        }
        // không phải admin trả về màn hình user
        return "chat/chatUser";
    }
    // Xử lý tin nhắn từ client
    @MessageMapping("/chat")
    public void sendMessage(ChatMessage chatMessage) {
        System.out.println("Recipient: " + chatMessage.getRecipient());
        System.out.println("Message content: " + chatMessage.getContent());
        System.out.println("Sending to: /user/" + chatMessage.getRecipient() + "/queue/messages");

        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipient(),
                "/queue/messages",
                chatMessage   // nội dung
        );
    }

}

