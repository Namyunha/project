package com.icia.project.dto;

import com.icia.project.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class ChatRoomDTO {
    //  채팅방을 구현하기 위한 DTO
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();
    // 채팅방은 입장한 클라이언트들의 정보를 가지고 있어야 하므로 WebsocketSession 정보 리스트를 멤버 필드로 가져야한다.

    @Builder
    public ChatRoomDTO(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessageDTO chatMessageDTO, ChatService chatService) {
        if (chatMessageDTO.getType().equals(ChatMessageDTO.MessageType.ENTER)) {
            sessions.add(session);
            chatMessageDTO.setMessage(chatMessageDTO.getSender() + "님이 입장했습니다.");
        }
        sendMessage(chatMessageDTO, chatService);
    }
    // 채팅방에서는 입장, 대화하기의 기능이 있으므로 handleAction을 통해 분기 처리 한다.
    // 입장 시에는 채팅룸의 session 정보에 클라이언트의 session 리스트에 추가해 놓았다가
    // 채팅룸에 메시지가 도착할 경우 채팅룸의 모든 session에 메시지를 발송하면 채팅이 완선
    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
