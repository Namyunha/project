package com.icia.project.dto;

import com.icia.project.Entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private String memberId;
    private String memberPass;
    private String memberName;
    private String memberPrivate;
    private String memberGender;
    private String memberEmail;
    private String memberPhone;
    private LocalDateTime createdAt;

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberPass(memberEntity.getMemberPass());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPrivate(memberEntity.getMemberPrivate());
        memberDTO.setMemberGender(memberEntity.getMemberGender());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDTO.setCreatedAt(memberEntity.getCreatedAt());
        return memberDTO;
    }


}




