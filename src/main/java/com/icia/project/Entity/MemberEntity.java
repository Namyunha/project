package com.icia.project.Entity;

import com.icia.project.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "member_table")
@Getter
@Setter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false, unique = true)
    private String memberId;
    @Column(length = 50, nullable = false)
    private String memberPass;
    @Column(length = 10, nullable = false)
    private String memberName;
    @Column(length = 20, nullable = false)
    private String memberPrivate;
    @Column(length = 10, nullable = false)
    private String memberGender;
    @Column(length = 20, nullable = false)
    private String memberEmail;
    @Column(length = 20, nullable = false)
    private String memberPhone;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPrivate(memberDTO.getMemberPrivate());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        return memberEntity;
    }
}
