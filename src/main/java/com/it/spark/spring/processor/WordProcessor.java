package com.it.spark_spring.processor;

import com.google.gson.Gson;
import com.it.spark_spring.utils.ProcessingService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.Map;

public class WordProcessor implements Processor {
    JavaSparkContext sc = ProcessingService.getSc();
    Gson gson = ProcessingService.getGson();
    @Override
    public void process(Exchange exchange) {

        String fileContent = exchange.getIn().getBody(String.class);
        JavaRDD<String> lines = sc.parallelize(Arrays.asList(fileContent.split("\n")));
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        Map<String, Long> wordCounts = words.countByValue();
        exchange.getIn().setBody(gson.toJson(wordCounts));
    }

}
