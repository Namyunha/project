package com.icia.project.Entity;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


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
    private String memberPhone;
}
