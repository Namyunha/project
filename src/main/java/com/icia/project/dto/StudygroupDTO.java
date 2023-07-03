package com.icia.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

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
    private String createdAt;
    private int fileAttached;

    private List<MultipartFile> groupFile;

    private String originalFileName;
    private String storedFileName;
}





