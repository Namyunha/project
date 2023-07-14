package com.icia.project.Entity;

import com.icia.project.dto.ReviewDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "review_table")
@Getter
@Setter
public class ReviewEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, length = 20)
    private String userId;

    @Column(nullable = false, length = 500)
    private String reviewContents;

    @Column(nullable = false)
    private Long rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity MemberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private StudygroupEntity StudyGroupEntity;

    public static ReviewEntity saveEntity(ReviewDTO reviewDTO, MemberEntity memberEntity, StudygroupEntity studygroupEntity) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setUserName(reviewDTO.getUserName());
        reviewEntity.setUserId(memberEntity.getMemberId());
        reviewEntity.setReviewContents(reviewDTO.getReviewContents());
        reviewEntity.setRate(reviewDTO.getRate());
        reviewEntity.setMemberEntity(memberEntity);
        reviewEntity.setStudyGroupEntity(studygroupEntity);
        return reviewEntity;
    }
}
