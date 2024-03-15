package com.it.spark_spring.api;

import com.it.spark_spring.services.SparkService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SparkAPI extends RouteBuilder {
    @Override
    public void configure() {

        rest()
            .post("/wordCount")
            .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
            .type(String.class)
                .to(SparkService.WORD_COUNT)
            .outType(String.class)
            .responseMessage()
                .code(HttpStatus.OK.value())
            .endResponseMessage()
        ;
    }
}
