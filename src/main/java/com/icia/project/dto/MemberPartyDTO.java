package com.icia.project.dto;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.PartyUserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberPartyDTO {
    private static int number = 0;
    private Long id = (long) number;
    private String memberId;
    private String memberName;
    private String memberPrivate;
    private String memberGender;
    private String memberEmail;
    private String memberPhone;
    private String userPosition;

    public MemberPartyDTO() {
        number++;
    }

    public static MemberPartyDTO toDTO(MemberEntity memberEntity, PartyUserEntity partyUserEntity) {
        MemberPartyDTO memberPartyDTO = new MemberPartyDTO();
        memberPartyDTO.setUserPosition(partyUserEntity.getUserPosition());
        memberPartyDTO.setMemberId(memberEntity.getMemberId());
        memberPartyDTO.setMemberName(memberEntity.getMemberName());
        memberPartyDTO.setMemberPrivate(memberEntity.getMemberPrivate());
        memberPartyDTO.setMemberGender(memberEntity.getMemberGender());
        memberPartyDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberPartyDTO.setMemberPhone(memberEntity.getMemberPhone());
        return memberPartyDTO;
    }

}
