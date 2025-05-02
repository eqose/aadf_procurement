package com.team.services;

import com.team.models.dtos.AiExtractResult;

import java.util.Map;

public interface AiService {
    AiExtractResult extractFields(String documentUrl);

    Map<String, Integer> suggestScores(Map<String, Integer> criteria);
}
