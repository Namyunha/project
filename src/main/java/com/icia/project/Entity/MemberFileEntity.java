package com.icia.project.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_file_table")
@Getter
@Setter
public class MemberFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String storedFileName;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    MemberEntity MemberEntity;

    public static MemberFileEntity saveFileEntity(String originalFileName, String storedFileName, MemberEntity saveFile) {
        MemberFileEntity memberFileEntity = new MemberFileEntity();
        memberFileEntity.setOriginalFileName(originalFileName);
        memberFileEntity.setStoredFileName(storedFileName);
        memberFileEntity.setMemberEntity(saveFile);
        return memberFileEntity;
    }
}
