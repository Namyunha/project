package com.icia.project.dto;


import com.icia.project.Entity.PartyUserEntity;
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
    private String isAdmitted;
    private String userPosition;
    private Long partyId;
    private Long memberId;

    public static PartyUserDTO toDTO(PartyUserEntity partyUserEntity) {
        PartyUserDTO partyUserDTO = new PartyUserDTO();
        partyUserDTO.setId(partyUserEntity.getId());
        partyUserDTO.setPartyTitle(partyUserEntity.getPartyTitle());
        partyUserDTO.setUserName(partyUserEntity.getUserName());
        if (partyUserEntity.getIsAdmitted() == "true") {
            partyUserDTO.setIsAdmitted("true");
        } else {
            partyUserDTO.setIsAdmitted("false");
        }
        partyUserDTO.setPartyId(partyUserEntity.getStudygroupEntity().getId());
        if (partyUserEntity.getUserPosition() == "운영자") {
            partyUserDTO.setUserPosition("운영자");
        } else if (partyUserEntity.getUserPosition() == "메니저") {
            partyUserDTO.setUserPosition("메니저");
        } else if (partyUserEntity.getUserPosition() == "회원") {
            partyUserDTO.setUserPosition("회원");
        } else if (partyUserEntity.getUserPosition() == "비회원") {
            partyUserDTO.setUserPosition("비회원");
        } else {
            partyUserDTO.setUserPosition("");
        }
        partyUserDTO.setMemberId(partyUserEntity.getMemberEntity().getId());
        return partyUserDTO;
    }


}
