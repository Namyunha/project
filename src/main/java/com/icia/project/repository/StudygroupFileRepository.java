package com.icia.project.repository;

import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.Entity.StudygroupFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudygroupFileRepository extends JpaRepository<StudygroupFileEntity, Long> {
}
