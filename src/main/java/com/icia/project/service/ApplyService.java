package com.icia.project.service;

import com.icia.project.Entity.ApplyEntity;
import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.dto.ApplyDTO;
import com.icia.project.dto.PartyUserDTO;
import com.icia.project.dto.StudygroupDTO;
import com.icia.project.repository.ApplyRepository;
import com.icia.project.repository.MemberRepository;
import com.icia.project.repository.StudygroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Transactional
    public List<StudygroupDTO> findAllById(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        List<ApplyEntity> applyEntityList = applyRepository.findAllByMemberEntity(memberEntity);
        List<StudygroupDTO> studygroupDTOList = new ArrayList<>();
        for (ApplyEntity applyEntity : applyEntityList) {
            StudygroupEntity studygroupEntity = studygroupRepository.findById(applyEntity.getStudygroupEntity().getId()).orElseThrow(() -> new NoSuchElementException());
            StudygroupDTO studygroupDTO = StudygroupDTO.toDTO(studygroupEntity);
            studygroupDTOList.add(studygroupDTO);
        }
        return studygroupDTOList;
    }


    // 로그인한 유저가 등록한 모임의 신청내역리스트
    public List<ApplyDTO> findApplyByGroupId(Long id) {
        StudygroupEntity studygroupEntity = studygroupRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        List<ApplyDTO> applyDTOList = new ArrayList<>();
        List<ApplyEntity> applyEntityList = applyRepository.findAllByStudygroupEntity(studygroupEntity);
        if (applyEntityList.isEmpty()) {
            return applyDTOList;
        } else {
            for (ApplyEntity applyEntity : applyEntityList) {
                ApplyDTO applyDTO = ApplyDTO.toDTO(applyEntity);
                applyDTOList.add(applyDTO);
            }
            return applyDTOList;
        }

    }

    public ApplyDTO findById(Long id) {
        ApplyEntity applyEntity = applyRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        System.out.println("In Service, applyEntity = " + applyEntity.getId());
        ApplyDTO applyDTO = ApplyDTO.toDTO(applyEntity);
        System.out.println("In Service, applyDTO = " + applyDTO);
        return applyDTO;
    }


    public ApplyDTO findApplyByMemberIdAndPartyId(ApplyDTO applyDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(applyDTO.getMemberId());
        Optional<StudygroupEntity> optionalStudygroupEntity = studygroupRepository.findById(applyDTO.getPartyId());
        if (optionalMemberEntity.isPresent() && optionalStudygroupEntity.isPresent()) {
            MemberEntity applyMemberEntity = optionalMemberEntity.get();
            StudygroupEntity applyGroupEntity = optionalStudygroupEntity.get();
            ApplyEntity applyEntity = applyRepository.findByMemberEntityAndStudygroupEntity(applyMemberEntity, applyGroupEntity);
            ApplyDTO userApply = ApplyDTO.toDTO(applyEntity);
            System.out.println("In service, userApply = " + userApply);
            return userApply;
        } else {
            return null;
        }
    }

    public void updateAuthorization(PartyUserDTO partyUserDTO) {
        System.out.println("In ApplyService, partyUserDTO = " + partyUserDTO);
        MemberEntity memberEntity = memberRepository.findById(partyUserDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        StudygroupEntity studygroupEntity = studygroupRepository.findById(partyUserDTO.getPartyId()).orElseThrow(() -> new NoSuchElementException());

        ApplyEntity applyEntity = applyRepository.findByMemberEntityAndStudygroupEntity(memberEntity, studygroupEntity);
        ApplyEntity updatedApplyEntity = ApplyEntity.toUpdateAuthorization(applyEntity, partyUserDTO);
        applyRepository.save(updatedApplyEntity);
    }

    public ApplyDTO findByMemberId(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        MemberEntity memberEntity = optionalMemberEntity.get();
        ApplyEntity applyEntity = applyRepository.findByMemberEntity(memberEntity);
        if (applyEntity == null) {
            return null;
        } else {
            ApplyDTO applyDTO = ApplyDTO.toDTO(applyEntity);
            return applyDTO;
        }
    }

    //    디테일창에 신청중, 모임신청 버튼 표시하기
    public ApplyDTO findApplyBtn(Long userId, Long groupId) {
        MemberEntity memberEntity = memberRepository.findById(userId).orElse(null);
        StudygroupEntity studygroupEntity = studygroupRepository.findById(groupId).orElse(null);

        if (memberEntity == null || studygroupEntity == null) {
            return null;
        }

        ApplyEntity applyEntity = applyRepository.findByMemberEntityAndStudygroupEntity(memberEntity, studygroupEntity);

        if (applyEntity == null) {
            return null;
        } else {
            ApplyDTO applyDTO = ApplyDTO.toDTO(applyEntity);
            System.out.println("서비스에 있는 applyDTO = " + applyDTO);
            return applyDTO;
        }
    }
}


