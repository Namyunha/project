package com.icia.project.repository;

import com.icia.project.Entity.MemberEntity;
import com.icia.project.Entity.StudygroupEntity;
import com.icia.project.Entity.StudygroupFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudygroupRepository extends JpaRepository<StudygroupEntity, Long> {
    List<StudygroupEntity> findAllByMemberEntity(MemberEntity memberEntity);

    List<StudygroupEntity> findAllById(Long id);



    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying
    @Query(value = "update StudygroupEntity s set s.userCount=s.userCount+1 where s.id=:id")
    void updateCount(@Param("id") Long id);
}
