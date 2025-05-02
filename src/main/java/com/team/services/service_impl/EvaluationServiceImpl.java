package com.team.services.service_impl;

import com.team.models.Evaluation;
import com.team.models.Offer;
import com.team.repository.EvaluationRepository;
import com.team.services.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationRepository repo;

    public EvaluationServiceImpl(EvaluationRepository repo) {
        this.repo = repo;
    }

    public Evaluation score(Evaluation eval) {
        eval.setScoredAt(LocalDateTime.now());
        return repo.save(eval);
    }

    public Optional<Evaluation> findById(Long id) {
        return repo.findById(id);
    }

    public List<Evaluation> findByOffer(Offer offer) {
        return repo.findAllByOffer_Id(offer.getId());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
