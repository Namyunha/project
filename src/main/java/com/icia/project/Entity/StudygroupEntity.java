package com.icia.project.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "studygroup_table")
public class StudygroupEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long partyPersonnel;

    @Column(nullable = false, length = 10)
    private String partyHost;

    @Column(nullable = false, length = 500)
    private String partyDetail;

    @Column(nullable = false, length = 10)
    private String partyTitle;

    @Column(nullable = false, length = 20)
    private String partyMethod;

    @Column(nullable = false, length = 20)
    private String partyTimes;

    @Column
    private String partyCategory;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "studygroupEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StudygroupFileEntity> studygroupFileEntityList;
}
