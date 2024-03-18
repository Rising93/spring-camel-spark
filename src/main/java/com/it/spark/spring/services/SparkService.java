package com.it.spark.spring.services;

import com.it.spark.spring.aggregate.OutputAggregate;
import com.it.spark.spring.processor.WordAggregateProcessor;
import com.it.spark.spring.processor.WordProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Service;

@Service
public class SparkService extends RouteBuilder {
    public static final String WORD_COUNT = "direct:wordCount";

    @Override
    public void configure(){

        from(WORD_COUNT)
            .process(new WordProcessor())
            .split(body(), new OutputAggregate())
                .process(new WordAggregateProcessor())
            .end()
            .marshal().json(JsonLibrary.Gson)
        ;

    }

}
