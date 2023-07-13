package com.icia.project.controller;

import com.icia.project.dto.*;
import com.icia.project.service.ApplyService;
import com.icia.project.service.MemberService;
import com.icia.project.service.PartyUserService;
import com.icia.project.service.StudygroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/partyUser")
public class PartyUserController {
    private final PartyUserService partyUserService;
    private final StudygroupService studygroupService;
    private final ApplyService applyService;
    private final MemberService memberService;

    @Transactional
    @PostMapping("/save")
    public ResponseEntity savePartyUser(@RequestBody PartyUserDTO partyUserDTO) {
        // 모임이 있는 유저 +1
        partyUserService.save(partyUserDTO);
        // 유저가 가입한 모임의 인원수 +1
        studygroupService.updateCount(partyUserDTO.getPartyId());
        // 유저의 신청서 현황 수정
        applyService.updateAuthorization(partyUserDTO);
        return new ResponseEntity<>(partyUserDTO, HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity rejectPartyUser(@RequestBody PartyUserDTO partyUserDTO) {
        applyService.updateAuthorization(partyUserDTO);
        return new ResponseEntity<>(partyUserDTO, HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public String goRoom(HttpSession session, @PathVariable Long id, Model model) {
        String loginUser = (String) session.getAttribute("loginId");
        StudygroupDTO studygroupDTO = studygroupService.findById(id);
        MemberDTO memberDTO = memberService.findByMemberId(loginUser);
        List<MemberPartyDTO> memberDTOList = partyUserService.findAllByPartyId(studygroupDTO.getId());
        model.addAttribute("list", memberDTOList);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("studygroupDTO", studygroupDTO);
        return "studyGroupPages/studyGroupRoom";
    }

    @PostMapping("/detail")
    public ResponseEntity checkUser(@RequestBody MemberPartyDTO memberPartyDTO) {
        MemberPartyDTO checkMemberPartyDTO = memberService.findByMemberIdAndPartyId(memberPartyDTO.getMemberId(), memberPartyDTO.getPartyId());
        System.out.println("컨트롤러 checkMemberPartyDTO = " + checkMemberPartyDTO);
        return new ResponseEntity<>(checkMemberPartyDTO, HttpStatus.OK);
    }

    @PutMapping("/resign")
    public ResponseEntity deleteUser(@RequestBody PartyUserDTO partyUserDTO) {
        System.out.println("leaveDTO = " + partyUserDTO);
        partyUserService.resign(partyUserDTO);
        return new ResponseEntity<>(partyUserDTO, HttpStatus.OK);
    }

    @GetMapping("/chatRoom")
    public String openRoom() {
        return "/memberPages/chattingRoom";
    }
}
