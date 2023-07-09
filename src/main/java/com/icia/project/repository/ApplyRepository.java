package com.icia.project.repository;

import com.icia.project.Entity.ApplyEntity;
import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.StudygroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {
    List<ApplyEntity> findAllByMemberEntity(MemberEntity memberEntity);
    ApplyEntity findByMemberEntity(MemberEntity memberEntity);

    ApplyEntity findByHostEntity(MemberEntity memberEntity);

    List<ApplyEntity> findAllByStudygroupEntity(StudygroupEntity studygroupEntity);


    ApplyEntity findByMemberEntityAndStudygroupEntity(MemberEntity memberEntity, StudygroupEntity studygroupEntity);
}
