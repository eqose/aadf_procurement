package com.team.services;

import com.team.models.Offer;
import com.team.models.Tender;

import java.util.List;
import java.util.Optional;

public interface OfferService {
    Offer submit(Offer offer);

    Optional<Offer> findById(Long id);

    List<Offer> findByTender(Tender tender);

    void delete(Long id);
}
