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
    private boolean isAdmitted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    StudygroupEntity studygroupEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    MemberEntity memberEntity;

    public static PartyUserEntity toSaveEntity(PartyUserDTO partyUserDTO) {
        PartyUserEntity partyUserEntity = new PartyUserEntity();
        partyUserEntity.setPartyTitle(partyUserDTO.getPartyTitle());
        partyUserEntity.setUserName(partyUserDTO.getUserName());
        partyUserEntity.setAdmitted(true);
        return partyUserEntity;
    }
}
