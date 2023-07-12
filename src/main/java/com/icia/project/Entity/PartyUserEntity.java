package com.icia.project.Entity;

import com.icia.project.dto.PartyUserDTO;
import com.icia.project.dto.StudygroupDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "party_user_table")
public class PartyUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String partyTitle;

    @Column(nullable = false, length = 30)
    private String userName;

    @Column
    private String isAdmitted;

    @Column
    private String userPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    StudygroupEntity studygroupEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    MemberEntity memberEntity;

    public static PartyUserEntity toSaveEntity(PartyUserDTO partyUserDTO, MemberEntity memberEntity, StudygroupEntity studygroupEntity) {
        PartyUserEntity partyUserEntity = new PartyUserEntity();
        partyUserEntity.setPartyTitle(partyUserDTO.getPartyTitle());
        partyUserEntity.setUserName(partyUserDTO.getUserName());
        System.out.println("Entity에 있는 partyUserDTO.getIsAdmitted() = " + partyUserDTO.getIsAdmitted());
        if (partyUserDTO.getIsAdmitted().equals("true")) {
            partyUserEntity.setIsAdmitted("true");
            partyUserEntity.setUserPosition(partyUserDTO.getUserPosition());
        } else if (partyUserDTO.getIsAdmitted().equals("false")) {
            partyUserEntity.setIsAdmitted("false");
            partyUserEntity.setUserPosition("비회원");
        }
        partyUserEntity.setMemberEntity(memberEntity);
        partyUserEntity.setStudygroupEntity(studygroupEntity);

        return partyUserEntity;
    }

    public static PartyUserEntity saveAdmin(StudygroupEntity studygroupEntity, MemberEntity memberEntity) {
        PartyUserEntity partyUserEntity = new PartyUserEntity();
        partyUserEntity.setPartyTitle(studygroupEntity.getPartyTitle());
        partyUserEntity.setUserName(memberEntity.getMemberName());
        partyUserEntity.setIsAdmitted("true");
        partyUserEntity.setMemberEntity(memberEntity);
        partyUserEntity.setStudygroupEntity(studygroupEntity);
        partyUserEntity.setUserPosition("운영자");
        return partyUserEntity;
    }
}
