package com.icia.project.repository;

import com.icia.project.Entity.ApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {
}
