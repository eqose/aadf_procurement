package com.team.repository;

import com.team.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long>, JpaSpecificationExecutor<Skills> {

    List<Skills> findByUser_Id (Long id);
}
