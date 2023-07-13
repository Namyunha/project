package com.icia.project.controller;


import com.icia.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) throws IOException {
        System.out.println("code = " + code);
        String access_Token = userService.getKaKaoAccessToken(code);
        userService.getKaKaoAccessToken(access_Token);
    }
}
