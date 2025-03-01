package com.AB2_10.AB2_10.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PythonMLService {

    @Value("${python.ml.api.url}")
    private String pythonMlApiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> detectPiiWithML(String text) {
        Map<String, String> request = new HashMap<>();
        request.put("text", text);

        // Call Python ML API
        return restTemplate.postForObject(pythonMlApiUrl, request, Map.class);
    }
}