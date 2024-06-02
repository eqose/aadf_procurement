package com.team.machine_learning_model;

import com.team.entity.User;
import com.team.entity.UserInformation;
import com.team.repository.UserInformationRepository;
import com.team.repository.UserRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceModuleML {
    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;

    public ServiceModuleML(UserRepository userRepository,
                           UserInformationRepository userInformationRepository) {
        this.userRepository = userRepository;
        this.userInformationRepository = userInformationRepository;
    }

    public String splitUsersToGroups() {
//        this.updateCSV();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URI("http://127.0.0.1:5000/split_users");

            // Construct match_fields parameter as a JSON object
            JSONObject matchFields = new JSONObject();
            matchFields.put("age", "num");
            matchFields.put("profession", "cat");

            // Create a JSON object for the request body
            JSONObject requestBody = new JSONObject();
            requestBody.put("match_fields", matchFields);

            // Convert JSON object to string
            String requestBodyString = requestBody.toString();

            // Create the HTTP POST request
            HttpPost request = new HttpPost(uri);

            // Set the request body
            StringEntity entity = new StringEntity(requestBodyString);
            request.setEntity(entity);
            request.setHeader("Content-Type", "application/json");

            // Execute the request and handle the response
            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                try (InputStream inputStream = responseEntity.getContent()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                }
            }
            return null;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBestGroup(String userData) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URI("http://127.0.0.1:5000/predict_best_team");
            HttpPost request = new HttpPost(uri);
            StringEntity entity = new StringEntity(userData);
            request.setEntity(entity);

            request.setHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(request);

            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                try (InputStream inputStream = responseEntity.getContent()) {
                    byte[] buffer = new byte[1024];
                    StringBuilder stringBuilder = new StringBuilder();
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        stringBuilder.append(new String(buffer, 0, bytesRead));
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCSV() {
        List<UserInformation> infos = this.userInformationRepository.findAll();
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String csvFile = Paths.get(desktopPath, "dummy_data.csv").toString();
        try {
            FileWriter writer = new FileWriter(csvFile, true); // Set true for appending data to existing file
            // Write your data to the CSV file
            writer.append("id,age,gender,qualification,education,profession,position,company,experience,location\n");
            infos.forEach(el -> {
                try {
                    writer.append("\n")
                            .append(String.valueOf(el.getUser().getId()))
                            .append(",")
                            .append(String.valueOf(el.getAge()))
                            .append(",")
                            .append(el.getGender())
                            .append(",")
                            .append(el.getQualification())
                            .append(",")
                            .append(el.getEducation())
                            .append(",")
                            .append(el.getProfession())
                            .append(",")
                            .append(el.getPosition())
                            .append(",")
                            .append(el.getCompany())
                            .append(",")
                            .append(String.valueOf(el.getExperience()))
                            .append(",")
                            .append(el.getLocation());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            writer.flush();
            writer.close();
            System.out.println("CSV file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
