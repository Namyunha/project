package com.icia.project.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String boardCategory;
    private String boardPassword;
//    private String createdAt;
    private Long memberId;
    private int boardHits;
    private int fileAttached;
}
