package com.icia.project.controller;


import com.icia.project.Entity.MemberEntity;
import com.icia.project.dto.ApplyDTO;
import com.icia.project.dto.MemberDTO;
import com.icia.project.dto.StudygroupDTO;
import com.icia.project.service.ApplyService;
import com.icia.project.service.MemberService;
import com.icia.project.service.StudygroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final ApplyService applyService;
    private final StudygroupService studygroupService;

    @GetMapping("/searchId")
    public ResponseEntity searchId() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchPw")
    public ResponseEntity searchPw() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/save")
    public String save() {
        return "/memberPages/memberSave";
    }

    //    @PostMapping("/save")
//    public ResponseEntity saveParam(@RequestBody MemberDTO memberDTO) throws IOException {
//        System.out.println("Controller: memberDTO = " + memberDTO);
//        memberService.save(memberDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/save")
    public String saveParam(@RequestBody MemberDTO memberDTO) throws IOException {
        System.out.println("Controller: memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "redirect:login/";
    }

    @GetMapping("/list")
    public String memberList(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        System.out.println("Controller: memberDTOList = " + memberDTOList);
        model.addAttribute("list", memberDTOList);
        return "/memberPages/memberList";
    }

    @PostMapping("/duCheck")
    public ResponseEntity duCheck(@RequestBody MemberDTO memberDTO) {
        if (memberService.findEmail(memberDTO)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("loginId");
        MemberDTO loginUser = memberService.findByMemberId(loginId);
        model.addAttribute("loginUser", loginUser);
//      로그인한 유저가 호스트인 경우
        ApplyDTO applyDTO = applyService.findByHostId(loginUser.getId());
        System.out.println("컨트롤러에있는 applyDTO = " + applyDTO);
        if (applyDTO == null) {
            model.addAttribute("applyDTO", "");
        } else {
            model.addAttribute("applyDTO", applyDTO);
        }
//      로그인한 유저가 신청자인 경우
        ApplyDTO applyUserDTO = applyService.findByApplyUserId(loginUser.getId());
        if(applyUserDTO == null) {
            model.addAttribute("applyUserDTO", "");
        } else {
            model.addAttribute("applyUserDTO", applyUserDTO);
        }
        return "/memberPages/memberMain";
    }

    //  등록모임리스트'
    @GetMapping("/groupList/{id}")
    public ResponseEntity groupList(@PathVariable Long id) {
        List<StudygroupDTO> studygroupDTOList = studygroupService.findAllById(id);
        return new ResponseEntity<>(studygroupDTOList, HttpStatus.OK);
    }

    //  신청모임리스트
    @Transactional
    @GetMapping("/applyGroupList/{id}")
    public ResponseEntity applyGroupList(@PathVariable Long id) {
        List<StudygroupDTO> applyStudyGroupList = applyService.findAllById(id);
        return new ResponseEntity<>(applyStudyGroupList, HttpStatus.OK);
    }

    // 로그인한 유저가 등록한 모임의 신청내역리스트
    @GetMapping("/applyHistory/{id}")
    public ResponseEntity historyList(@PathVariable Long id) {
        System.out.println("id = " + id);
        List<ApplyDTO> applyDTOList = applyService.findApplyByGroupId(id);
        return new ResponseEntity<>(applyDTOList, HttpStatus.OK);
    }


    //  신청리스트
    @GetMapping("/applyUser/{id}")
    public ResponseEntity applyUser(@PathVariable Long id) {
        ApplyDTO applyDTO = applyService.findById(id);
        System.out.println("applyDTO = " + applyDTO);
        MemberDTO applyMemberDTO = memberService.findById(applyDTO.getMemberId());
        System.out.println("컨트롤러에 있는 신청 유저  = " + applyMemberDTO);
        return new ResponseEntity<>(applyMemberDTO, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        MemberDTO memberDTO = memberService.findById(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
//        myPage에서 업데이트 창이랑 정보창 띄우기
    }


    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody MemberDTO memberDTO) {
        memberService.updateUser(memberDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
