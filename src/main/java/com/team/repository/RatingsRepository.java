package com.team.repository;

import com.team.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Long> {

    Ratings findByTeam_Id (Long teamId);
    Ratings findByUser_Id (Long userId);
}
