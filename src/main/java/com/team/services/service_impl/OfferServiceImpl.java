package com.team.services.service_impl;

import com.team.models.Offer;
import com.team.models.Tender;
import com.team.repository.OfferRepository;
import com.team.services.OfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {
    private final OfferRepository repo;

    public OfferServiceImpl(OfferRepository repo) {
        this.repo = repo;
    }

    public Offer submit(Offer offer) {
        offer.setSubmittedAt(LocalDateTime.now());
        return repo.save(offer);
    }

    public Optional<Offer> findById(Long id) {
        return repo.findById(id);
    }

    public List<Offer> findByTender(Tender tender) {
        return repo.findAllByTender_Id(tender.getId());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
