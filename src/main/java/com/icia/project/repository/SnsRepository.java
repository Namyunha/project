package com.icia.project.repository;

import com.icia.project.Entity.SnsMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnsRepository extends JpaRepository<SnsMemberEntity, Long> {
}
