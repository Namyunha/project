package com.icia.project.service;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.PartyUserEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.dto.PartyUserDTO;
import com.icia.project.repository.MemberRepository;
import com.icia.project.repository.PartyUserRepository;
import com.icia.project.repository.StudygroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PartyUserService {
    private final PartyUserRepository partyUserRepository;
    private final MemberRepository memberRepository;
    private final StudygroupRepository studygroupRepository;

    public void save(PartyUserDTO partyUserDTO) {
        System.out.println("In partyUserService  = " + partyUserDTO);
        MemberEntity memberEntity = memberRepository.findById(partyUserDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        StudygroupEntity studygroupEntity = studygroupRepository.findById(partyUserDTO.getPartyId()).orElseThrow(() -> new NoSuchElementException());
        PartyUserEntity partyUserEntity = PartyUserEntity.toSaveEntity(partyUserDTO, memberEntity, studygroupEntity);
        System.out.println("서비스에 있는 partyUserEntity.getIsAdmitted() = " + partyUserEntity.getIsAdmitted());
        partyUserRepository.save(partyUserEntity);
    }
}
