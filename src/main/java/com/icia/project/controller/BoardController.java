package com.icia.project.controller;


import com.icia.project.dto.BoardDTO;
import com.icia.project.dto.MemberDTO;
import com.icia.project.service.BoardService;
import com.icia.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping
    public String board() {
        return "/boardPages/boardMain";
    }

    @GetMapping("/save")
    public String saveForm(HttpSession session, Model model) {
        String loginDTO = (String) session.getAttribute("loginId");
        MemberDTO memberDTO = memberService.findByMemberId(loginDTO);
        if (memberDTO == null) {
            model.addAttribute("loginDTO", "");
        } else {
            model.addAttribute("loginDTO", memberDTO);
        }
        return "/boardPages/boardSave";
    }

    @PostMapping("/save")
    public ResponseEntity saveParam(@RequestBody BoardDTO boardDTO) {
        System.out.println("컨트롤러에 있는 saveParam: boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return new ResponseEntity<>(boardDTO, HttpStatus.OK);
    }
}


