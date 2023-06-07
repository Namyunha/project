package com.icia.project.controller;


import com.icia.project.dto.MemberDTO;
import com.icia.project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "redirectURI", defaultValue = "/member/mypage") String redirectURI,
                        Model model) {
        System.out.println("MemberController.loginForm");
        System.out.println("redirectURI = " + redirectURI);
        model.addAttribute("redirectURI", redirectURI);
        return "/memberPages/memberLogin";
    }


    //    @PostMapping("/login")
//    public ResponseEntity loginParam(@RequestBody MemberDTO memberDTO, HttpSession session) {
//        if (memberService.login(memberDTO)) {
//            session.setAttribute("loginId", memberDTO.getMemberId());
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @PostMapping("/login")
    public String loginParam(@ModelAttribute MemberDTO memberDTO, HttpSession session, @RequestParam("redirectURI") String redirectURI) {
        System.out.println("MemberController.memberLogin");
        System.out.println("URI" + redirectURI);
        if (memberService.login(memberDTO)) {
            session.setAttribute("loginId", memberDTO.getMemberId());
            return "redirect:" + redirectURI;
        } else {
            return "memberPages/memberLogin";
        }
    }

    @PostMapping("/login/axios")
    public ResponseEntity memberLoginAxios(@RequestBody MemberDTO memberDTO, HttpSession session) throws Exception {
        memberService.loginAxios(memberDTO);
        session.setAttribute("loginId", memberDTO.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
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

    @GetMapping("/mypage")
    public String myPage() {
        return "/memberPages/memberMain";
    }

    @GetMapping("/list")
    public String memberList(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        System.out.println("Controller: memberDTOList = " + memberDTOList);
        model.addAttribute("list", memberDTOList);
        return "/memberPages/memberList";
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
