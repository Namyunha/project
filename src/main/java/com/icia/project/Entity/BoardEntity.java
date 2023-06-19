package com.icia.project.Entity;

import com.icia.project.dto.BoardDTO;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Table(name = "board_table")
@Entity
@RequiredArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column(length = 20, nullable = false)
    private String boardTitle;

    @Column(length = 500, nullable = false)
    private String boardContents;

    @Column(length = 10, nullable = false)
    private String boardCategory;

    @Column(length = 20, nullable = false)
    private String boardPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;

    @Column
    private int boardHits;

    @Column
    private int fileAttached;



    public static BoardEntity toSaveEntity(MemberEntity memberEntity , BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardCategory(boardDTO.getBoardCategory());
        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0);
        return boardEntity;
    }

//    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

}
