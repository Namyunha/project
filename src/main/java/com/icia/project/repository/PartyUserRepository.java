package com.icia.project.repository;

import com.icia.project.Entity.PartyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyUserRepository extends JpaRepository<PartyUserEntity, Long> {
}
