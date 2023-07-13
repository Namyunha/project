package com.icia.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    @GetMapping("/save/{id}")
    public String saveReview(@PathVariable Long id) {
        return "/studyGroupPages/studyGroupReview";
    }

}
