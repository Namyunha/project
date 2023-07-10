package com.icia.project.controller;


import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.dto.ApplyDTO;
import com.icia.project.dto.StudygroupDTO;
import com.icia.project.service.ApplyService;
import com.icia.project.service.StudygroupService;
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
    private final StudygroupService studygroupService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody ApplyDTO applyDTO) {
        System.out.println("applyDTO = " + applyDTO);
        StudygroupDTO studygroupDTO = studygroupService.findById(applyDTO.getPartyId());
        System.out.println("studygroupDTO.getUserCount() = " + studygroupDTO.getUserCount());
        if (studygroupDTO.getUserCount() == studygroupDTO.getPartyPersonnel() || studygroupDTO.getUserCount() > studygroupDTO.getPartyPersonnel()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            applyService.save(applyDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}










