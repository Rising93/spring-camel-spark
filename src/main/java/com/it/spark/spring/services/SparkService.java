package com.it.spark.spring.services;

import com.it.spark.spring.processor.AirportCountryProcessor;
import com.it.spark.spring.processor.AirportLatitudeProcessor;
import com.it.spark.spring.processor.WordProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class SparkService extends RouteBuilder {
    public static final String WORD_COUNT = "direct:wordCount";
    public static final String AIRPORT_BY_LATITUDE = "direct:airportByLatitude";
    public static final String AIRPORT_BY_COUNTRY = "direct:airportByCountry";
    @Override
    public void configure(){


        from(WORD_COUNT)
            .unmarshal().mimeMultipart()
            .process(new WordProcessor())
        ;

        from(AIRPORT_BY_LATITUDE)
            .unmarshal().mimeMultipart()
            .process(new AirportLatitudeProcessor())
        ;

        from(AIRPORT_BY_COUNTRY)
            .unmarshal().mimeMultipart()
            .process(new AirportCountryProcessor())
        ;

    }

}
