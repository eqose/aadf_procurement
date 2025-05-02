package com.team.services.service_impl;

import com.team.models.DocumentVersion;
import com.team.models.Offer;
import com.team.models.Tender;
import com.team.repository.DocumentVersionRepository;
import com.team.services.DocumentVersionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentVersionServiceImpl implements DocumentVersionService {
    private final DocumentVersionRepository repo;

    public DocumentVersionServiceImpl(DocumentVersionRepository repo) {
        this.repo = repo;
    }

    public DocumentVersion upload(DocumentVersion dv) {
        dv.setUploadedAt(LocalDateTime.now());
        return repo.save(dv);
    }

    public Optional<DocumentVersion> findById(Long id) {
        return repo.findById(id);
    }

    public List<DocumentVersion> findByTender(Tender tender) {
        return repo.findAllByTender_Id(tender.getId());
    }

    public List<DocumentVersion> findByOffer(Offer offer) {
        return repo.findAllByOffer_Id(offer.getId());
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
