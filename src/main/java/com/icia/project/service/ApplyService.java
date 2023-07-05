package com.icia.project.service;

import com.icia.project.Entity.ApplyEntity;
import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.dto.ApplyDTO;
import com.icia.project.repository.ApplyRepository;
import com.icia.project.repository.MemberRepository;
import com.icia.project.repository.StudygroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final MemberRepository memberRepository;
    private final StudygroupRepository studygroupRepository;

    public void save(ApplyDTO applyDTO) {
        MemberEntity applyMember = memberRepository.findById(applyDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        MemberEntity hostMember = memberRepository.findById(applyDTO.getHostId()).orElseThrow(() -> new NoSuchElementException());
        StudygroupEntity party = studygroupRepository.findById(applyDTO.getPartyId()).orElseThrow(() -> new NoSuchElementException());
        ApplyEntity applyEntity = ApplyEntity.toSaveEntity(applyDTO, applyMember, hostMember, party);
        applyRepository.save(applyEntity);
    }
}
