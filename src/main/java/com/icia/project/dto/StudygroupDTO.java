package com.icia.project.dto;

import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.Entity.StudygroupFileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class StudygroupDTO {
    private Long id;
    private Long partyPersonnel;
    private String partyHost;
    private String partyDetail;
    private String partyTitle;
    private String partyMethod;
    private String partyTimes;
    private String partyCategory;
    private LocalDateTime partyCreatedTime;
    private LocalDateTime partyUpdatedTime;
    private Long hostId;
    private int userCount;

    private List<MultipartFile> groupFile;

    private String originalFileName;
    private String storedFileName;
    private int fileAttached;

    public static StudygroupDTO toDTO(StudygroupEntity studygroupEntity) {
        StudygroupDTO studygroupDTO = new StudygroupDTO();
        studygroupDTO.setId(studygroupEntity.getId());
        studygroupDTO.setPartyPersonnel(studygroupEntity.getPartyPersonnel());
        studygroupDTO.setPartyHost(studygroupEntity.getPartyHost());
        studygroupDTO.setPartyDetail(studygroupEntity.getPartyDetail());
        studygroupDTO.setPartyTitle(studygroupEntity.getPartyTitle());
        studygroupDTO.setPartyMethod(studygroupEntity.getPartyMethod());
        studygroupDTO.setPartyTimes(studygroupEntity.getPartyTimes());
        studygroupDTO.setPartyCategory(studygroupEntity.getPartyCategory());
        studygroupDTO.setPartyCreatedTime(studygroupEntity.getCreatedAt());
        studygroupDTO.setPartyUpdatedTime(studygroupEntity.getUpdatedAt());
        studygroupDTO.setHostId(studygroupEntity.getMemberEntity().getId());
        if (studygroupEntity.getFileAttached() == 1) {
            StudygroupFileEntity groupFile = studygroupEntity.getStudygroupFileEntityList().get(0);
            String originalFileName = groupFile.getOriginalFileName();
            String storedFileName = groupFile.getStoredFileName();
            studygroupDTO.setOriginalFileName(originalFileName);
            studygroupDTO.setStoredFileName(storedFileName);
            studygroupDTO.setFileAttached(1);
        } else {
            studygroupDTO.setFileAttached(0);
        }
        return studygroupDTO;
    }
}





