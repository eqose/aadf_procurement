package com.team.machine_learning_model;

import org.springframework.web.bind.annotation.*;


@RestController
public class ModelRequest {
    private final ServiceModuleML serviceModuleML;

    public ModelRequest(ServiceModuleML serviceModuleML) {
        this.serviceModuleML = serviceModuleML;
    }

    @GetMapping("/groups")
    public String generateUsers() {
        return this.serviceModuleML.splitUsersToGroups();
    }

    @GetMapping("/send-user")
    public String sendUser(@RequestParam String userData) {
//        String userData = "{\"gender\": \"male\", \"age\": 30, \"profession\": \"engineer\", \"location\": \"new york\"}";
        return this.serviceModuleML.getBestGroup(userData);
    }

}
