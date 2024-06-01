package com.team.machine_learning_model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MLInput {
    String theme;
    String data;
    String filters;
}
