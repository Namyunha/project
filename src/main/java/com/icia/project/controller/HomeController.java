package com.icia.project.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("loginId");
        model.addAttribute("loginId", loginId);
        return "index";
    }

}
