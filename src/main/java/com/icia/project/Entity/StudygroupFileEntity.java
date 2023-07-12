package com.icia.project.Entity;

import com.icia.project.dto.StudygroupDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "studygroup_file_table")
public class StudygroupFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studygroup_id")
    private StudygroupEntity StudyGroupEntity;

    public static StudygroupFileEntity save(StudygroupEntity studygroupEntity, String originalFileName, String storedFileName) {
        StudygroupFileEntity studygroupFileEntity = new StudygroupFileEntity();
        studygroupFileEntity.setStudyGroupEntity(studygroupEntity);
        studygroupFileEntity.setOriginalFileName(originalFileName);
        studygroupFileEntity.setStoredFileName(storedFileName);
        return studygroupFileEntity;
    }


    public static StudygroupFileEntity update(StudygroupFileEntity uStudygroupFileEntity, String originalFileName, String storedFileName, StudygroupEntity estudygroupEntity) {
        StudygroupFileEntity studygroupFileEntity = new StudygroupFileEntity();
        studygroupFileEntity.setId(uStudygroupFileEntity.getId());
        studygroupFileEntity.setStudyGroupEntity(estudygroupEntity);
        studygroupFileEntity.setOriginalFileName(originalFileName);
        studygroupFileEntity.setStoredFileName(storedFileName);
        return studygroupFileEntity;
    }
}
