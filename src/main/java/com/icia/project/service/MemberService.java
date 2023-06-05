package com.icia.project.service;


import com.icia.project.Entity.MemberEntity;
import com.icia.project.dto.MemberDTO;
import com.icia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
