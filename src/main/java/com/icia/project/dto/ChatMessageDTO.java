package com.icia.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessageDTO {
    //    채팅 메시지를 주고받기 위한 DTO
    //    메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    //     상황에 따라 채팅방 입장과 채팅방에 메시지 보내기, 두가지 상황이 있으므로 ENTER, TALK을 enum으로 선언
    }
    private MessageType type; // 메시지 타입
    private String roomId; // 방번호
    private String sender; // 메시지 보낸 사람
    private String message; // 메시지
}
