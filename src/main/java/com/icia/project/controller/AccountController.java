package com.icia.project.controller;

import com.icia.project.service.MailServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@RequiredArgsConstructor
@Controller
public class AccountController {
    private final MailServiceInter registerMail;

    @PostMapping("/login/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam String email) throws Exception {
        String code = registerMail.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }
}
