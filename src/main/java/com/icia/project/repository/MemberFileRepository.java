package com.icia.project.repository;

import com.icia.project.Entity.MemberFileEntity;
import com.icia.project.Entity.StudygroupFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFileRepository extends JpaRepository<MemberFileEntity, Long> {
}
