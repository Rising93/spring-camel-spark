package com.it.spark_spring.services;

import com.it.spark_spring.processor.WordProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Service;

@Service
public class SparkService extends RouteBuilder {
    public static final String WORD_COUNT = "direct:wordCount";
    @Override
    public void configure(){
        from(WORD_COUNT)
            .unmarshal().mimeMultipart()
            .process(new WordProcessor())
        ;

    }

}
