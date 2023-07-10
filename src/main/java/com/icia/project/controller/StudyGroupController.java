package com.icia.project.controller;

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
@RequestMapping("/studygroup")
public class StudyGroupController {
    private final StudygroupService studygroupService;
    private final MemberService memberService;
    private final ApplyService applyService;

    @Transactional
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        List<StudygroupDTO> studygroupDTOList = studygroupService.findAll();
        String loginUser = (String) session.getAttribute("loginId");
        System.out.println("studygroupDTOList = " + studygroupDTOList);
        if (loginUser == null) {
            model.addAttribute("loginUserId", 0);
            System.out.println("0");
        } else {
            MemberDTO memberDTO = memberService.findByMemberId(loginUser);
            System.out.println("memberDTO = " + memberDTO);
            model.addAttribute("loginUserId", memberDTO.getId());
        }
        model.addAttribute("groupList", studygroupDTOList);
        return "/studyGroupPages/studyGroupList";
    }

    @GetMapping("/save")
    public String save() {
        return "/studyGroupPages/studyGroupSave";
    }

    //  모임 등록
    @PostMapping("/save")
    public String saveGroup(@ModelAttribute StudygroupDTO studygroupDTO, HttpSession session) throws IOException {
        String loginId = (String) session.getAttribute("loginId");
        studygroupService.save(studygroupDTO, loginId);
        return "redirect:list";
    }

    //  모임 시간 저장
    @PostMapping("/axiosSave")
    public ResponseEntity axiosSave(@RequestBody StudygroupDTO studygroupDTO) {
        return new ResponseEntity<>(studygroupDTO.getPartyTimes(), HttpStatus.OK);
    }


    @Transactional
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, HttpSession session) {
        StudygroupDTO studygroupDTO = studygroupService.findById(id);
        String loginUser = (String) session.getAttribute("loginId");
        if (loginUser == null) {
            model.addAttribute("loginUser", "");
            model.addAttribute("loginUserId", 0);
        } else {
            MemberDTO loginUserDTO = memberService.findByMemberId(loginUser);
            System.out.println("컨트롤러에 있는 loginUserDTO = " + loginUserDTO);
            model.addAttribute("loginUser", loginUserDTO.getMemberName());
            model.addAttribute("loginUserId", loginUserDTO.getId());
            ApplyDTO applyDTO = applyService.findApplyBtn(loginUserDTO.getId(), studygroupDTO.getId());
            System.out.println("컨트롤러에 있는 applyDTO = " + applyDTO);
            if (applyDTO != null) {
                model.addAttribute("applyDTO", applyDTO);
            } else {
                model.addAttribute("applyDTO", null);
            }
        }
        model.addAttribute("group", studygroupDTO);
        return "/studyGroupPages/studyGroupDetail";
    }
}
