package com.team.services;

import com.team.entity.Ratings;
import com.team.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl {

    private final RatingsRepository ratingsRepository;

    @Autowired
    public RatingServiceImpl(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public Ratings saveRatings(Ratings ratings) {
        Ratings byTeamId = this.ratingsRepository.findByTeam_Id(ratings.getTeam().getId());
        if (byTeamId == null) {
            return this.ratingsRepository.save(ratings);
        } else {
            byTeamId.setLikes(ratings.getLikes());
            byTeamId.setDislikes(ratings.getDislikes());
            return this.ratingsRepository.save(byTeamId);
        }

    }

    public Ratings getRatingsByTeamId(Long teamId){
        return this.ratingsRepository.findByTeam_Id(teamId);
    }

    public Ratings getRatingsByUserId(Long userId){
        return this.ratingsRepository.findByUser_Id(userId);
    }
}
