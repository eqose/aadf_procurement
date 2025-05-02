package com.team.services.service_impl;

import com.team.models.dtos.AiExtractResult;
import com.team.services.AiService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AiServiceImpl implements AiService {
    private final RestTemplate rest;
    private static final String AI_BASE_URL = "http://localhost:8000";

    public AiServiceImpl(RestTemplateBuilder builder) {
        this.rest = builder.build();
    }

    @Override
    public AiExtractResult extractFields(String documentUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> req = new HttpEntity<>(Map.of("docUrl", documentUrl), headers);
        return rest.postForObject(AI_BASE_URL + "/extract", req, AiExtractResult.class);
    }

    @Override
    public Map<String, Integer> suggestScores(Map<String, Integer> criteria) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Integer>> req = new HttpEntity<>(criteria, headers);
        return rest.exchange(
                AI_BASE_URL + "/suggest-scores",
                org.springframework.http.HttpMethod.POST,
                req,
                new ParameterizedTypeReference<Map<String, Integer>>() {
                }
        ).getBody();
    }
}
