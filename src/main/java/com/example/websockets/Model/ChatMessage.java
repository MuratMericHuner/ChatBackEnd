package com.example.websockets.Model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    private String messageContent;
    private String sender;
    private MessageType messageType;
}
