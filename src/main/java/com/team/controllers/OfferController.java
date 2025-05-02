package com.team.controllers;

import com.team.models.Offer;
import com.team.models.Tender;
import com.team.services.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "http://localhost:4200")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public Offer submit(@RequestBody Offer offer) {
        return offerService.submit(offer);
    }

    @GetMapping
    public List<Offer> list() {
        return offerService.findByTender(null);
    }

    @GetMapping("/{id}")
    public Optional<Offer> get(@PathVariable Long id) {
        return offerService.findById(id);
    }

    @GetMapping("/tender/{tenderId}")
    public List<Offer> listByTender(@PathVariable Long tenderId) {
        Tender t = new Tender();
        return offerService.findByTender(t);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        offerService.delete(id);
    }
}
