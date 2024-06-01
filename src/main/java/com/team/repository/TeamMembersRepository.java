package com.team.repository;

import com.team.entity.TeamMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMembersRepository extends JpaRepository<TeamMembers, Long> {
    TeamMembers findByUser_IdAndTeam_Id(Long userId, Long teamId);
}
