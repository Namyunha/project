package com.icia.project.Entity;

import com.icia.project.dto.MemberDTO;
import com.icia.project.dto.StudygroupDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "member_table")
@Getter
@Setter
public class MemberEntity extends BaseEntity{
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
    @Column
    private int fileAttached;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

//    이미지파일 참조
    @OneToMany(mappedBy = "MemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberFileEntity>  memberFileEntityList = new ArrayList<>();

//    모임 참조
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StudygroupEntity> studygroupEntityList = new ArrayList<>();

//    신청자 참조
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ApplyEntity>  applyEntityList = new ArrayList<>();

//    호스트 참조
    @OneToMany(mappedBy = "hostEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ApplyEntity> hostApplyEntityList = new ArrayList<>();

//    가입한 모임 참조
    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PartyUserEntity> partyUserEntityList = new ArrayList<>();

//    가입한 모임 참조
    @OneToMany(mappedBy = "MemberEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();

    public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPrivate(memberDTO.getMemberPrivate());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setFileAttached(0);
        return memberEntity;
    }

    public static MemberEntity toUpdateEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDTO.getId());
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPrivate(memberDTO.getMemberPrivate());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setFileAttached(1);
        return memberEntity;
    }

    public static MemberEntity toSaveEntityWithFile(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberPass(memberDTO.getMemberPass());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberPrivate(memberDTO.getMemberPrivate());
        memberEntity.setMemberGender(memberDTO.getMemberGender());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPhone(memberDTO.getMemberPhone());
        memberEntity.setFileAttached(1);
        return memberEntity;
    }
}
