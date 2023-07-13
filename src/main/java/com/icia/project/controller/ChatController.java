package com.icia.project.controller;


import com.icia.project.dto.ChatRoomDTO;
import com.icia.project.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    @PostMapping
    public ChatRoomDTO createRoom(@RequestParam String name)  {
        System.out.println("name = " + name);
        return chatService.chatRoomDTO(name);
    }
    @GetMapping
    public List<ChatRoomDTO> findAllRoom(){
        return chatService.findAllRoom();
    }
}
