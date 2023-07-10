package com.icia.project.Entity;

import com.icia.project.dto.PartyUserDTO;
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
        } else if (partyUserDTO.getIsAdmitted().equals("false")) {
            partyUserEntity.setIsAdmitted("false");
        }
        partyUserEntity.setMemberEntity(memberEntity);
        partyUserEntity.setStudygroupEntity(studygroupEntity);
        return partyUserEntity;
    }
}
