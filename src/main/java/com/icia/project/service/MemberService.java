package com.icia.project.service;


import com.icia.project.Entity.MemberEntity;
import com.icia.project.dto.MemberDTO;
import com.icia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        memberRepository.save(memberEntity);
    }

    public boolean findEmail(MemberDTO memberDTO) {
        Optional<MemberEntity> memberEntity = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (memberEntity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberIdAndMemberPass(memberDTO.getMemberId(), memberDTO.getMemberPass());
        if (optionalMemberEntity.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
