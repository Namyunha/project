package com.icia.project.service;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.Entity.StudygroupFileEntity;
import com.icia.project.dto.MemberDTO;
import com.icia.project.dto.StudygroupDTO;
import com.icia.project.repository.MemberFileRepository;
import com.icia.project.repository.MemberRepository;
import com.icia.project.repository.StudygroupFileRepository;
import com.icia.project.repository.StudygroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudygroupService {
    private final StudygroupRepository studygroupRepository;
    private final StudygroupFileRepository studygroupFileRepository;
    private final MemberRepository memberRepository;

    public void save(StudygroupDTO studygroupDTO, String loginId) throws IOException {
        if (studygroupDTO.getGroupFile() == null || studygroupDTO.getGroupFile().get(0).isEmpty()) {
            System.out.println("서비스에 있는 loginId = " + loginId);
            Optional<MemberEntity> memberEntity = memberRepository.findByMemberId(loginId);
            MemberEntity loginMemberEntity = memberEntity.get();
            System.out.println("loginMemberEntity.getId() = " + loginMemberEntity.getId());
            StudygroupEntity studygroupEntity = StudygroupEntity.saveGroupEntity(studygroupDTO, loginMemberEntity);
            studygroupRepository.save(studygroupEntity);
        } else {
            System.out.println("서비스에 있는 loginId = " + loginId);
            Optional<MemberEntity> memberEntity = memberRepository.findByMemberId(loginId);
            MemberEntity loginMemberEntity = memberEntity.get();
            System.out.println("loginMemberEntity.getId() = " + loginMemberEntity.getId());
            StudygroupEntity studygroupEntity = StudygroupEntity.saveGroupEntityWithFile(studygroupDTO, loginMemberEntity);
            studygroupRepository.save(studygroupEntity);
            String originalFileName = studygroupDTO.getGroupFile().get(0).getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "D:\\Springboot_project_img\\" + storedFileName;
            studygroupDTO.getGroupFile().get(0).transferTo(new File(savePath));
            StudygroupFileEntity studygroupFileEntity = StudygroupFileEntity.save(studygroupEntity, originalFileName, storedFileName);
            studygroupFileRepository.save(studygroupFileEntity);
        }
    }

    @Transactional
    public List<StudygroupDTO> findAll() {
        List<StudygroupEntity> studygroupEntityList = studygroupRepository.findAll();
        List<StudygroupDTO> studygroupDTOList = new ArrayList<>();
        for (StudygroupEntity studygroupEntity : studygroupEntityList) {
            StudygroupDTO studygroupDTO = StudygroupDTO.toDTO(studygroupEntity);
            studygroupDTOList.add(studygroupDTO);
        }
        return studygroupDTOList;
    }

    @Transactional
    public StudygroupDTO findById(Long id) {
        StudygroupEntity studygroupEntity = studygroupRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        StudygroupDTO studygroupDTO = StudygroupDTO.toDTO(studygroupEntity);
        return studygroupDTO;
    }

    @Transactional
    public List<StudygroupDTO> findAllById(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        List<StudygroupEntity> studygroupEntityList = studygroupRepository.findAllByMemberEntity(memberEntity);
        List<StudygroupDTO> studygroupDTOList = new ArrayList<>();
        for (StudygroupEntity studygroupEntity : studygroupEntityList) {
            StudygroupDTO studygroupDTO = StudygroupDTO.toDTO(studygroupEntity);
            studygroupDTOList.add(studygroupDTO);
        }
        System.out.println("서비스에 있는 studygroupDTOList = " + studygroupDTOList);
        return studygroupDTOList;
    }
}


