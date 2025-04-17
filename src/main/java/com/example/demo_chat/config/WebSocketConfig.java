package com.example.demo_chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");// "/queue" cho tin nhắn riêng
        config.setApplicationDestinationPrefixes("/app");// Prefix tin nhắn client gửi tới server
        config.setUserDestinationPrefix("/user"); // Prefix tin nhắn gửi đến user cụ thể
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Cấu hình WebSocket endpoint
        registry.addEndpoint("/ws").setAllowedOrigins("http://localhost:8080/").withSockJS();
    }


//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws")
//                .setAllowedOrigins("http://localhost:8080")
//                .setHandshakeHandler(new DefaultHandshakeHandler() {
//                    @Override
//                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
//                        // Lấy sender từ URL (parameters trong WebSocket handshake)
//                        String username = request.getURI().getQuery(); // Ví dụ: sender=chat2
//                        if (username != null && username.startsWith("sender=")) {
//                            username = username.substring(7); // Lấy giá trị của sender
//                        } else {
//                            System.out.println("-----Không có sender trong URL, tạo ngẫu nhiên.");
//                            username = UUID.randomUUID().toString(); // Nếu không có sender, tạo ngẫu nhiên
//                        }
//                        System.out.println("Determined Principal: " + username);
//                        return new StompPrincipal(username);
//                    }
//
//                })
//                .withSockJS();
//    }


}
