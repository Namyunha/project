package com.icia.project.repository;

import com.icia.project.Entity.StudygroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudygroupRepository extends JpaRepository<StudygroupEntity, Long> {
}
