package com.icia.project.repository;


import com.icia.project.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //    select => findBy
    //    select * from member_table where member_table where member_email=?
    Optional<MemberEntity> findByMemberEmail(String memberEmail);

    //    select * from member_table where member_email=? and member_password=?
    Optional<MemberEntity> findByMemberEmailAndMemberPass(String memberEmail, String memberPassword);

    Optional<MemberEntity> findByMemberId(String memberId);
}
