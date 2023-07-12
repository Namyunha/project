package com.icia.project.repository;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.PartyUserEntity;
import com.icia.project.Entity.StudygroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyUserRepository extends JpaRepository<PartyUserEntity, Long> {
    PartyUserEntity findByStudygroupEntity(StudygroupEntity studygroupEntity);

    PartyUserEntity findByStudygroupEntityAndMemberEntity(StudygroupEntity studygroupEntity, MemberEntity memberEntity);

    List<PartyUserEntity> findAllByStudygroupEntity(StudygroupEntity studygroupEntity);
}
