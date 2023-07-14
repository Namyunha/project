package com.icia.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.icia.project.Entity.ReviewEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewDTO {
    private Long id;
    private String userId;
    private String userName;
    private String reviewContents;
    private Long rate;
    private Long groupId;
    private Long memberId;
    private String reviewCreatedTime;
    private LocalDateTime reviewUpdatedTime;

    public static ReviewDTO toDTO(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(reviewEntity.getId());
        reviewDTO.setUserId(reviewEntity.getUserId());
        reviewDTO.setUserName(reviewEntity.getUserName());
        reviewDTO.setRate(reviewEntity.getRate());
        reviewDTO.setReviewContents(reviewEntity.getReviewContents());
        reviewDTO.setGroupId(reviewEntity.getStudyGroupEntity().getId());
        reviewDTO.setMemberId(reviewEntity.getMemberEntity().getId());
        reviewDTO.setReviewCreatedTime(reviewEntity.getFormattedCreatedTime());
        reviewDTO.setReviewUpdatedTime(reviewEntity.getUpdatedAt());
        return reviewDTO;
    }
}




