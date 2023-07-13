package com.icia.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.project.dto.ChatMessageDTO;
import com.icia.project.dto.ChatRoomDTO;
import com.icia.project.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        ChatMessageDTO chatMessageDTO = objectMapper.readValue(payload, ChatMessageDTO.class);
        ChatRoomDTO room = chatService.findRoomById(chatMessageDTO.getRoomId());
        room.handleActions(session, chatMessageDTO, chatService);
    }

}

       