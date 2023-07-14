package com.icia.project.dto;

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
}




