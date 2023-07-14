package com.icia.project.repository;

import com.icia.project.Entity.ReviewEntity;
import com.icia.project.Entity.StudygroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
