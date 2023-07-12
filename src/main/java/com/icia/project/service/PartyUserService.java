package com.icia.project.service;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.PartyUserEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.dto.MemberDTO;
import com.icia.project.dto.MemberPartyDTO;
import com.icia.project.dto.PartyUserDTO;
import com.icia.project.repository.MemberRepository;
import com.icia.project.repository.PartyUserRepository;
import com.icia.project.repository.StudygroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public PartyUserDTO findByGroupIdAndMemberId(Long groupId, Long memberId) {
        StudygroupEntity studygroupEntity = studygroupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException());
        MemberEntity memberEntity = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException());
        PartyUserEntity partyUserEntity = partyUserRepository.findByStudygroupEntityAndMemberEntity(studygroupEntity, memberEntity);
        PartyUserDTO partyUserDTO = PartyUserDTO.toDTO(partyUserEntity);
        System.out.println("파티 유저 서비스에있는 partyUserDTO = " + partyUserDTO);
        return partyUserDTO;
    }

    public List<MemberPartyDTO> findAllByPartyId(Long id) {
        StudygroupEntity studygroupEntity = studygroupRepository.findById(id).orElseThrow();
        List<PartyUserEntity> partyUserEntityList = partyUserRepository.findAllByStudygroupEntity(studygroupEntity);
        List<MemberPartyDTO> memberDTOList = new ArrayList<>();
        for (PartyUserEntity partyUserEntity : partyUserEntityList) {
            MemberEntity memberEntity = memberRepository.findById(partyUserEntity.getMemberEntity().getId()).orElseThrow(() -> new NoSuchElementException());
            MemberPartyDTO memberPartyDTO = MemberPartyDTO.toDTO(memberEntity, partyUserEntity);
            memberDTOList.add(memberPartyDTO);
        }
        return memberDTOList;
    }

//    public List<MemberDTO> findAllByPartyId(Long id) {
//        StudygroupEntity studygroupEntity = studygroupRepository.findById(id).orElseThrow();
//        List<PartyUserEntity> partyUserEntityList = partyUserRepository.findAllByStudygroupEntity(studygroupEntity);
//        List<PartyUserDTO> partyUserDTOList = new ArrayList<>();
//        for (PartyUserEntity partyUserEntity : partyUserEntityList) {
//            PartyUserDTO partyUserDTO = PartyUserDTO.toDTO(partyUserEntity);
//            partyUserDTOList.add(partyUserDTO);
//        }
//        return null;
//    }
}
