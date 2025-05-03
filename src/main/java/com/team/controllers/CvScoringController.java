package com.team.controllers;

import com.team.models.dtos.CvScoresResponseDto;
import com.team.models.dtos.RequirementDto;
import com.team.services.CvScoringService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cv")
@CrossOrigin(origins = "http://localhost:4200")
public class CvScoringController {
    private final CvScoringService scoringService;

    public CvScoringController(CvScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping(value = "/score", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CvScoresResponseDto scoreCvs(
            @RequestPart("requirements") List<RequirementDto> requirements,
            @RequestPart("files") List<MultipartFile> cvs
    ) throws Exception {
        return scoringService.scoreCvs(requirements, cvs);
    }
}
