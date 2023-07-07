package com.icia.project.service;

import com.icia.project.Entity.ApplyEntity;
import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.dto.ApplyDTO;
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

    public ApplyDTO findByUserId(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        ApplyEntity applyEntity = applyRepository.findByHostEntity(memberEntity);
        if (applyEntity == null) {
            return null;
        } else {
            ApplyDTO applyDTO = ApplyDTO.toDTO(applyEntity);
            return applyDTO;
        }
    }

    public List<ApplyDTO> findApplyById(Long id) {
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

}
