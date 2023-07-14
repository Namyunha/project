package com.icia.project.dto;

import com.icia.project.Entity.ReviewEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewDTO {
    private Long id;
    private String userName;
    private String reviewContents;
    private Long rate;
    private Long groupId;
    private Long memberId;

    public static ReviewDTO toDTO(ReviewEntity reviewEntity) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(reviewEntity.getId());
        return null;
    }
}




