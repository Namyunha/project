package com.icia.project.controller;

import com.icia.project.dto.StudygroupDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/studygroup")
public class StudyGroupController {
    @GetMapping("/list")
    public String list() {
        return "/studyGroupPages/studyGroupList";
    }

    @GetMapping("/save")
    public String save() {
        return "/studyGroupPages/studyGroupSave";
    }

    @PostMapping("/save")
    public String saveGroup(@ModelAttribute StudygroupDTO studygroupDTO) {
        System.out.println("studygroupDTO = " + studygroupDTO);
        return "/redirect:/list";
    }

}
