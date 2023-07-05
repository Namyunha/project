package com.icia.project.controller;

import com.icia.project.dto.MemberDTO;
import com.icia.project.dto.StudygroupDTO;
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

    @Transactional
    @GetMapping("/list")
    public String list(Model model) {
        List<StudygroupDTO> studygroupDTOList = studygroupService.findAll();
        System.out.println("studygroupDTOList = " + studygroupDTOList);
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
}
