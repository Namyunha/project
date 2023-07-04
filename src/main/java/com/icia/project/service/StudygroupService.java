package com.icia.project.service;

import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.Entity.StudygroupFileEntity;
import com.icia.project.dto.StudygroupDTO;
import com.icia.project.repository.StudygroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StudygroupService {
    private final StudygroupRepository studygroupRepository;
    private final StudygroupFileRepository studygroupFileRepository;

    public void save(StudygroupDTO studygroupDTO) throws IOException {
        if (studygroupDTO.getGroupFile() == null || studygroupDTO.getGroupFile().get(0).isEmpty()) {
            StudygroupEntity studygroupEntity = StudygroupEntity.saveGroupEntity(studygroupDTO);
            studygroupRepository.save(studygroupEntity);
        } else {
            StudygroupEntity studygroupEntity = StudygroupEntity.saveGroupEntityWithFile(studygroupDTO);
            studygroupRepository.save(studygroupEntity);
            String originalFileName = studygroupDTO.getGroupFile().get(0).getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "D:\\Springboot_project_img\\" + storedFileName;
            studygroupDTO.getGroupFile().get(0).transferTo(new File(savePath));
            StudygroupFileEntity studygroupFileEntity = StudygroupFileEntity.save(studygroupEntity, originalFileName, storedFileName);
            studygroupFileRepository.save(studygroupFileEntity);
        }
    }
}
