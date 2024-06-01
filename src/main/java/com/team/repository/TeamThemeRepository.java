package com.team.repository;


import com.team.entity.TeamTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamThemeRepository extends JpaRepository<TeamTheme, Long> {

}
