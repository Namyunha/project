package com.icia.project.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApplyDTO {
    private Long id;
    private String memberName;
    private String applyContents;
    private String applyParty;
    private Long memberId;
    private Long partyId;
    private Long hostId;
}
