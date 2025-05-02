package com.team.services.service_impl;

import com.team.models.Decision;
import com.team.models.Tender;
import com.team.repository.DecisionRepository;
import com.team.services.DecisionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class DecisionServiceImpl implements DecisionService {
    private final DecisionRepository repo;

    public DecisionServiceImpl(DecisionRepository repo) {
        this.repo = repo;
    }

    public Decision decide(Decision decision) {
        decision.setDecidedAt(LocalDateTime.now());
        return repo.save(decision);
    }

    public Optional<Decision> findByTender(Tender tender) {
        return repo.findByTender_Id(tender.getId());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
