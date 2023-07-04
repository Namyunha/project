package com.icia.project.dto;

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

    private List<MultipartFile> groupFile;

    private String originalFileName;
    private String storedFileName;
    private int fileAttached;
}





