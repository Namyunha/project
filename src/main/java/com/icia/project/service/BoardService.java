package com.icia.project.service;

import com.icia.project.Entity.BoardEntity;
import com.icia.project.Entity.MemberEntity;
import com.icia.project.dto.BoardDTO;
import com.icia.project.dto.MemberDTO;
import com.icia.project.repository.BoardRepository;
import com.icia.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Long save(BoardDTO boardDTO) {
        System.out.println("서비스에있는 boardDTO = " + boardDTO);
        MemberEntity memberEntity = memberRepository.findById(boardDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        BoardEntity boardEntity = BoardEntity.toSaveEntity(memberEntity, boardDTO);
        return boardRepository.save(boardEntity).getId();
    }


    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            BoardDTO boardDTO = BoardDTO.toDTO(boardEntity);
            boardDTOList.add(boardDTO);
        }
        return boardDTOList;
    }
}
