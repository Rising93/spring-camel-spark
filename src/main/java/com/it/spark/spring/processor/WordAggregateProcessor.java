package com.it.spark.spring.processor;

import com.google.gson.Gson;
import com.it.spark.spring.utils.ProcessingService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.Map;

public class WordAggregateProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {
        JavaSparkContext sc = ProcessingService.getSc();
        Gson gson = ProcessingService.getGson();

        String fileContent = exchange.getIn().getBody(String.class);

        JavaRDD<String> words = sc
                .parallelize(Arrays.asList(fileContent.split("\n")))
                .flatMap(line -> Arrays.asList(line.split(" "))
                        .iterator())
                .filter(word -> !word.isEmpty());

        Map<String, Long> wordCounts = words.countByValue();
        exchange.getIn().setBody(gson.toJson(wordCounts));
    }

}
