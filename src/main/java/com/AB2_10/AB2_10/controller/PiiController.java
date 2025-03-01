package com.AB2_10.AB2_10.controller;

import com.AB2_10.AB2_10.service.PiiDetectionService;
import com.AB2_10.AB2_10.service.PythonMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/pii")
public class PiiController {

    @Autowired
    private PiiDetectionService piiDetectionService;

    @Autowired
    private PythonMLService pythonMLService;

    @PostMapping(value = "/detect", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> detectPii(@RequestParam("file") MultipartFile file) throws IOException {
        // Step 1: Extract text from the file
        String text = piiDetectionService.extractText(file);

        // Step 2: Perform regex-based PII detection
        return piiDetectionService.detectPiiWithRegex(text);
    }
}