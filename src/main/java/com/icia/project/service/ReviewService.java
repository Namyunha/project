package com.icia.project.service;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.ReviewEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.dto.ReviewDTO;
import com.icia.project.repository.MemberRepository;
import com.icia.project.repository.ReviewRepository;
import com.icia.project.repository.StudygroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StudygroupRepository studygroupRepository;

    public void reviewSave(ReviewDTO reviewDTO) {
        MemberEntity memberEntity = memberRepository.findById(reviewDTO.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        StudygroupEntity studygroupEntity = studygroupRepository.findById(reviewDTO.getGroupId()).orElseThrow(() -> new NoSuchElementException());
        ReviewEntity reviewEntity = ReviewEntity.saveEntity(reviewDTO, memberEntity, studygroupEntity);
        reviewRepository.save(reviewEntity);
    }

    public List<ReviewDTO> findAllById(Long groupId) {
        StudygroupEntity studygroupEntity = studygroupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException());
        List<ReviewEntity> reviewEntityList = studygroupEntity.getReviewEntityList();
        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for(ReviewEntity reviewEntity : reviewEntityList){
            ReviewDTO reviewDTO = ReviewDTO.toDTO(reviewEntity);
        }
        return null;
    }
}
