package com.it.spark.spring.api;

import com.it.spark.spring.services.SparkService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SparkAPI extends RouteBuilder {
    @Override
    public void configure() {

        rest().clientRequestValidation(true)
            .post("/wordCount")
            .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .type(String.class)
                .to(SparkService.WORD_COUNT)
            .outType(String.class)
            .responseMessage()
                .code(HttpStatus.OK.value())
            .endResponseMessage()

            .post("/airportByLatitude")
            .param().name("latitude").type(RestParamType.query).description("Latitude").dataType("string").required(true).endParam()
            .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .type(String.class)
                .to(SparkService.AIRPORT_BY_LATITUDE)
            .outType(String.class)
            .responseMessage()
                .code(HttpStatus.OK.value())
            .endResponseMessage()

            .post("/airportByCountry")
            .param().name("country").type(RestParamType.query).description("Country").dataType("string").required(true).endParam()
            .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
            .produces(MediaType.APPLICATION_JSON_VALUE)
            .type(String.class)
                .to(SparkService.AIRPORT_BY_COUNTRY)
            .outType(String.class)
            .responseMessage()
                .code(HttpStatus.OK.value())
            .endResponseMessage()


        ;
    }
}
