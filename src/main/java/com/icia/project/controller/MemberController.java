package com.icia.project.controller;


import com.icia.project.dto.MemberDTO;
import com.icia.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public ResponseEntity loginParam(@RequestBody MemberDTO memberDTO, HttpSession session) {
        if (memberService.login(memberDTO)) {
            session.setAttribute("loginId", memberDTO.getMemberId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/save")
    public String save() {
        return "/memberPages/memberSave";
    }

    @PostMapping("/save")
    public ResponseEntity saveParam(@RequestBody MemberDTO memberDTO) {
        System.out.println("Controller: memberDTO = " + memberDTO);
        memberService.save(memberDTO);
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

    @PostMapping("/duCheck")
    public ResponseEntity duCheck(@RequestBody MemberDTO memberDTO) {
        System.out.println("memberDTO.getMemberId() = " + memberDTO.getMemberId());
        if (memberService.findEmail(memberDTO)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
