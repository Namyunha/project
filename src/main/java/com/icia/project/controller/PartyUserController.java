package com.icia.project.controller;

import com.icia.project.dto.PartyUserDTO;
import com.icia.project.service.ApplyService;
import com.icia.project.service.PartyUserService;
import com.icia.project.service.StudygroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/partyUser")
public class PartyUserController {
    private final PartyUserService partyUserService;
    private final StudygroupService studygroupService;
    private final ApplyService applyService;
    @Transactional
    @PostMapping("/save")
    public ResponseEntity savePartyUser(@RequestBody PartyUserDTO partyUserDTO) {
        System.out.println("ParyUserController에 있는 partyUserDTO = " + partyUserDTO);
        // 모임이 있는 유저 +1
        partyUserService.save(partyUserDTO);
        // 유저가 가입한 모임의 인원수 +1
        studygroupService.updateCount(partyUserDTO.getPartyId());
        // 유저의 신청서 현황 수정
        applyService.updateAuthorization(partyUserDTO);

        return new ResponseEntity<>(partyUserDTO, HttpStatus.OK);
    }

    @PostMapping("/reject")
    public ResponseEntity rejectPartyUser(@RequestBody PartyUserDTO partyUserDTO) {
        System.out.println("In Controller, partyUserDTO = " + partyUserDTO);
        applyService.updateAuthorization(partyUserDTO);
        return new ResponseEntity<>(partyUserDTO, HttpStatus.OK);
    }
}
