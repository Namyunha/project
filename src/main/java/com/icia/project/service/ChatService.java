package com.icia.project.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.project.dto.ChatRoomDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ObjectMapper objectMapper;
    private Map<String, ChatRoomDTO> chatRooms;
    // 채팅방 Map은 서버에 생성된 모든 채팅방의 정보를 모아둔 구조체이다.

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoomDTO> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoomDTO findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoomDTO chatRoomDTO(String name) {
        System.out.println("ChatService에 있는 name = " + name);
        String randomId = UUID.randomUUID().toString();
        System.out.println("randomId = " + randomId);
        ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder().roomId(randomId).name(name).build();
        System.out.println("서비스에있는 chatRoomDTO = " + chatRoomDTO);
        chatRooms.put(randomId, chatRoomDTO);
        return chatRoomDTO;
    }
    // 채팅방 생성 Random UUID로 구별 ID를 가진 채팅방 객체를 생성하고 채팅방 Map에 추가


    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
    // 지정한 Websocket 세션에 메시지를 발송


}
