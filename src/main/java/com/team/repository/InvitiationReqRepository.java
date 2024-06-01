package com.team.repository;

import com.team.entity.InviteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitiationReqRepository extends JpaRepository<InviteRequest, Long> {
}
