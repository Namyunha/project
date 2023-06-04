package com.icia.project.controller;


import com.icia.project.dto.MemberDTO;
import com.icia.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "/memberPages/memberLogin";
    }

    @PostMapping("/login")
    public String loginParam(){
        return "redirect:/myPage";
    }
//    프로젝트가 정해지거나, 게시판이 만들어졌을 때 움직일 곳 (다시 만져야 함)
    @GetMapping("/save")
    public String save() {
        return "/memberPages/memberSave";
    }

    @PostMapping("/save")
    public ResponseEntity saveParam(@RequestBody MemberDTO memberDTO) {
        System.out.println("Controller: memberDTO = " + memberDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "/memberPages/myPage";
    }

    @GetMapping("/update")
    public String update() {
        return "/memberPages/memberUpdate";
    }

    @PutMapping("/{id}")
    public String updateParam() {
        return "redirect:/member/myPages";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
