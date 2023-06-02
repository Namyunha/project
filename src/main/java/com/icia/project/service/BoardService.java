package com.icia.project.service;

import com.icia.project.Entity.BoardEntity;
import com.icia.project.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
}
