package com.it.spark_spring.utils;

import com.google.gson.Gson;
import lombok.Getter;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessingService {
    @Getter
    private static JavaSparkContext sc;

    @Getter
    private static Gson gson;
    @Autowired
    private ProcessingService(JavaSparkContext sc, Gson gson) {
        ProcessingService.sc = sc;
        ProcessingService.gson = gson;
    }

}
