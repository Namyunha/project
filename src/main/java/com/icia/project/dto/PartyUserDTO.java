package com.icia.project.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PartyUserDTO {
    private Long id;
    private String partyTitle;
    private String userName;
    private boolean isAdmitted;
    private Long partyId;
    private Long memberId;
}
