package com.icia.project.controller;


import com.icia.project.dto.ApplyDTO;
import com.icia.project.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody ApplyDTO applyDTO) {
        System.out.println("applyDTO = " + applyDTO);
        applyService.save(applyDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
