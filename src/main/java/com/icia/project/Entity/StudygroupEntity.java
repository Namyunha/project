package com.icia.project.Entity;


import com.icia.project.dto.StudygroupDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "studygroup_table")
public class StudygroupEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long partyPersonnel;

    @Column(nullable = false, length = 10)
    private String partyHost;

    @Column(nullable = false, length = 500)
    private String partyDetail;

    @Column(nullable = false, length = 10)
    private String partyTitle;

    @Column(nullable = false, length = 20)
    private String partyMethod;

    @Column(nullable = false, length = 20)
    private String partyTimes;

    @Column
    private String partyCategory;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "StudygroupEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StudygroupFileEntity> studygroupFileEntityList;

    public static StudygroupEntity saveGroupEntity(StudygroupDTO studygroupDTO) {
        StudygroupEntity studygroupEntity = new StudygroupEntity();
        studygroupEntity.setPartyPersonnel(studygroupDTO.getPartyPersonnel());
        studygroupEntity.setPartyHost(studygroupDTO.getPartyHost());
        studygroupEntity.setPartyDetail(studygroupDTO.getPartyDetail());
        studygroupEntity.setPartyTitle(studygroupDTO.getPartyTitle());
        studygroupEntity.setPartyMethod(studygroupDTO.getPartyMethod());
        studygroupEntity.setPartyTimes(studygroupDTO.getPartyTimes());
        studygroupEntity.setPartyCategory(studygroupDTO.getPartyCategory());
        studygroupEntity.setFileAttached(0);
        return studygroupEntity;
    }

    public static StudygroupEntity saveGroupEntityWithFile(StudygroupDTO studygroupDTO) {
        StudygroupEntity studygroupEntity = new StudygroupEntity();
        studygroupEntity.setPartyPersonnel(studygroupDTO.getPartyPersonnel());
        studygroupEntity.setPartyHost(studygroupDTO.getPartyHost());
        studygroupEntity.setPartyDetail(studygroupDTO.getPartyDetail());
        studygroupEntity.setPartyTitle(studygroupDTO.getPartyTitle());
        studygroupEntity.setPartyMethod(studygroupDTO.getPartyMethod());
        studygroupEntity.setPartyTimes(studygroupDTO.getPartyTimes());
        studygroupEntity.setPartyCategory(studygroupDTO.getPartyCategory());
        studygroupEntity.setFileAttached(1);
        return studygroupEntity;
    }
}



