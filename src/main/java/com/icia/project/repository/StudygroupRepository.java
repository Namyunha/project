package com.icia.project.repository;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.StudygroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudygroupRepository extends JpaRepository<StudygroupEntity, Long> {
    List<StudygroupEntity> findAllByMemberEntity(MemberEntity memberEntity);

    List<StudygroupEntity> findAllById(Long id);
}
