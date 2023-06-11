package com.icia.project.dto;


import com.icia.project.Entity.BoardEntity;
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

    public static BoardDTO toDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardCategory(boardEntity.getBoardCategory());
        boardDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDTO.setMemberId(boardEntity.getMemberEntity().getId());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setFileAttached(boardEntity.getFileAttached());
        return boardDTO;
    }
}
