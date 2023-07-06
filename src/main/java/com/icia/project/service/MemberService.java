package com.icia.project.service;


import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.MemberFileEntity;
import com.icia.project.dto.MemberDTO;
import com.icia.project.repository.MemberFileRepository;
import com.icia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberFileRepository memberFileRepository;

    public void save(MemberDTO memberDTO) throws IOException {
        if (memberDTO.getMemberProfile() == null || memberDTO.getMemberProfile().get(0).isEmpty()) {
            MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
            memberRepository.save(memberEntity);
        } else {
            MemberEntity memberEntity = MemberEntity.toSaveEntityWithFile(memberDTO);
            System.out.println("서비스에 있는 memberEntity.getMemberName() = " + memberEntity.getMemberName());
            MemberEntity saveFile = memberRepository.save(memberEntity);
            List<MultipartFile> memberFile = memberDTO.getMemberProfile();
            String originalFileName = memberFile.get(0).getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "C:\\Springboot_project_img\\" + storedFileName;
            memberFile.get(0).transferTo(new File(savePath));
            MemberFileEntity memberFileEntity = MemberFileEntity.saveFileEntity(originalFileName, storedFileName, saveFile);
            memberFileRepository.save(memberFileEntity);
        }
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

    public void loginAxios(MemberDTO memberDTO) {
        // chaining method (체이닝 메서드)
        memberRepository.findByMemberIdAndMemberPass(memberDTO.getMemberId(), memberDTO.getMemberPass())
                .orElseThrow(() -> new NoSuchElementException("이메일 또는 비밀번호가 틀립니다"));
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity);
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id).orElseThrow(() -> new NoSuchElementException("아이디를 조회할 수 없습니다."));
        return MemberDTO.toDTO(memberEntity);
    }


    //  id로 memberDTO 가져오기
    public MemberDTO findByMemberId(String loginDTO) {
        MemberEntity memberEntity = memberRepository
                .findByMemberId(loginDTO).orElseThrow(() -> new NoSuchElementException());
        if (memberEntity == null) {
            return null;
        } else {
            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity);
            return memberDTO;
        }
    }

    public Long updateUser(MemberDTO memberDTO) {
        System.out.println("서비스에 있는 업데이트 memberDTO = " + memberDTO);
        MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
        return memberRepository.save(memberEntity).getId();
    }


    public Long findUserId(String loginId) {
        MemberEntity memberEntity = memberRepository
                .findByMemberId(loginId).orElseThrow(() -> new NoSuchElementException());
        if (memberEntity == null) {
            int intValue = 0;
            long longValue = intValue;
            return longValue;
        } else {
            MemberDTO memberDTO = MemberDTO.toDTO(memberEntity);
            return memberDTO.getId();
        }
    }
}







