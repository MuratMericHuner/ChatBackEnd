package com.example.websockets.config;

import com.example.websockets.Model.ChatMessage;
import com.example.websockets.Model.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RequiredArgsConstructor
@Component
@Slf4j
public class WebSocketEventListener {
    private final SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = headerAccessor.getSessionAttributes().get("username").toString();
        if(userName!=null){
            log.info("User disconnected : {}", userName);
            ChatMessage  chatMessage = ChatMessage.builder().messageType(MessageType.LEAVE).sender(userName).build();
            messageSendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}
