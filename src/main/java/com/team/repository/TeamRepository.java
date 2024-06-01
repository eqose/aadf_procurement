package com.team.repository;

import com.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team> {

    @Query(value = "SELECT t FROM Team t join TeamMembers m where m.user.id = ?")
    List<Team> getTeamsByUserId (Long id);

    List<Team> getTeamsByTheme_Id (Long themeId);

}
