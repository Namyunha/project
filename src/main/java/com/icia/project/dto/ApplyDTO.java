package com.icia.project.dto;


import com.icia.project.Entity.ApplyEntity;
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
    private String isAuthorized;
    private Long memberId;
    private Long partyId;
    private Long hostId;

    public static ApplyDTO toDTO(ApplyEntity applyEntity) {
        ApplyDTO applyDTO = new ApplyDTO();
        applyDTO.setId(applyEntity.getId());
        applyDTO.setMemberName(applyEntity.getUserName());
        applyDTO.setApplyContents(applyEntity.getApplyContents());
        applyDTO.setApplyParty(applyEntity.getApplyParty());
        if (applyEntity.getIsAuthorized() == null) {
            applyDTO.setIsAuthorized("승인 대기중");
        } else if (applyEntity.getIsAuthorized() == "true") {
            applyDTO.setIsAuthorized("승인 허가");
        } else if (applyEntity.getIsAuthorized() == "false") {
            applyDTO.setIsAuthorized("승인 거절");
        }
        applyDTO.setMemberId(applyEntity.getMemberEntity().getId());
        applyDTO.setPartyId(applyEntity.getStudygroupEntity().getId());
        applyDTO.setHostId(applyEntity.getHostEntity().getId());
        return applyDTO;
    }
}
