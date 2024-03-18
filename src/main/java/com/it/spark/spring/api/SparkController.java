package com.it.spark.spring.api;

import com.it.spark.spring.services.SparkService;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class SparkController {
    @Autowired
    ProducerTemplate producerTemplate;
    @PostMapping("/wordCount")
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        producerTemplate.sendBody(SparkService.WORD_COUNT, files);
        return ResponseEntity.ok(producerTemplate.requestBody(SparkService.WORD_COUNT, files, String.class));
    }
}
