package com.team.services.service_impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.team.models.dtos.CvScoresResponseDto;
import com.team.models.dtos.RequirementDto;
import com.team.services.CvScoringService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class CvScoringServiceImpl implements CvScoringService {
    private final WebClient client;

    public CvScoringServiceImpl(WebClient cvScoringClient) {
        this.client = cvScoringClient;
    }

    @Override
    public CvScoresResponseDto scoreCvs(List<RequirementDto> requirements, List<MultipartFile> cvs) throws IOException {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        // Add requirements as JSON string
        String reqJson = new com.fasterxml.jackson.databind.ObjectMapper()
                .writeValueAsString(requirements);
        formData.add("requirements", reqJson);
        // Add each CV file
        for (MultipartFile file : cvs) {
            ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }

                @Override
                public long contentLength() {
                    return file.getSize();
                }
            };
            formData.add("files", resource);
        }

        Mono<CvScoresResponseDto> response = client.post()
                .uri("/score-cvs")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(formData))
                .retrieve()
                .bodyToMono(CvScoresResponseDto.class);

        return response.block();
    }
}
