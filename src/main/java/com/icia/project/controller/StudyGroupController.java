package com.icia.project.controller;

import com.icia.project.dto.StudygroupDTO;
import com.icia.project.service.StudygroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/studygroup")
public class StudyGroupController {
    private final StudygroupService studygroupService;
    @GetMapping("/list")
    public String list() {
        return "/studyGroupPages/studyGroupList";
    }

    @GetMapping("/save")
    public String save() {
        return "/studyGroupPages/studyGroupSave";
    }

//  모임 등록
    @PostMapping("/save")
    public String saveGroup(@ModelAttribute StudygroupDTO studygroupDTO) throws IOException {
        System.out.println("studygroupDTO = " + studygroupDTO);
        studygroupService.save(studygroupDTO);
        return "redirect:list";
    }

//  모임 시간 저장
    @PostMapping("/axiosSave")
    public ResponseEntity axiosSave(@RequestBody StudygroupDTO studygroupDTO) {
        return new ResponseEntity<>(studygroupDTO.getPartyTimes(), HttpStatus.OK);
    }
}
