package com.icia.project.Entity;

import com.icia.project.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sns_user_table")
public class SnsMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberEmail;

    @Column
    private String memberName;

    @Column
    private String memberGender;

    public static SnsMemberEntity toEntity(MemberDTO memberDTO) {
        SnsMemberEntity snsMemberEntity = new SnsMemberEntity();
        snsMemberEntity.setMemberEmail(memberDTO.getMemberEmail());
        snsMemberEntity.setMemberName(memberDTO.getMemberName());
        snsMemberEntity.setMemberGender(memberDTO.getMemberGender());
        return snsMemberEntity;
    }
}
