package com.icia.project.Entity;


import com.icia.project.dto.ApplyDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "apply_table")
public class ApplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String userName;

    @Column(nullable = false, length = 100)
    private String applyContents;

    @Column(nullable = false, length = 10)
    private String applyParty;

    @Column
    private String isAuthorized;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity memberEntity;

    @JoinColumn(name = "party_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private StudygroupEntity studygroupEntity;

    @JoinColumn(name = "host_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MemberEntity hostEntity;

    public static ApplyEntity toSaveEntity(ApplyDTO applyDTO, MemberEntity applyMember, MemberEntity hostMember, StudygroupEntity party) {
        ApplyEntity applyEntity = new ApplyEntity();
        applyEntity.setUserName(applyDTO.getMemberName());
        applyEntity.setApplyContents(applyDTO.getApplyContents());
        applyEntity.setApplyParty(applyDTO.getApplyParty());
        applyEntity.setMemberEntity(applyMember);
        applyEntity.setHostEntity(hostMember);
        applyEntity.setStudygroupEntity(party);
        return applyEntity;
    }
}
