package com.team.services.service_impl;

import com.team.models.Tender;
import com.team.repository.TenderRepository;
import com.team.services.TenderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TenderServiceImpl implements TenderService {
    private final TenderRepository repo;

    public TenderServiceImpl(TenderRepository repo) {
        this.repo = repo;
    }

    public Tender create(Tender t) {
        return repo.save(t);
    }

    public Optional<Tender> findById(Long id) {
        return repo.findById(id);
    }

    public List<Tender> findActive() {
        return repo.findByPublishedTrueAndDeadlineAfter(LocalDateTime.now());
    }

    public Tender publish(Long id) {
        Tender t = repo.findById(id).orElse(new Tender());
        t.setPublished(true);
        return repo.save(t);
    }

    public Tender update(Tender t) {
        return repo.save(t);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
