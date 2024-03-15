package com.it.spark.spring.processor;

import com.google.gson.Gson;
import com.it.spark.spring.utils.ProcessingService;
import com.it.spark.spring.utils.Utils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.*;
import java.util.stream.Collectors;

public class AirportCountryProcessor implements Processor {
    @Override
    public void process(Exchange exchange){
        JavaSparkContext sc = ProcessingService.getSc();
        Gson gson = ProcessingService.getGson();
        String fileContent = exchange.getIn().getBody(String.class);
        String country = exchange.getIn().getHeader("country", String.class);

        JavaRDD<List<Map<String, Object>>> airportsRDD = sc
                .parallelize(Arrays.asList(fileContent.split("\n")))
                .filter(line -> line.split(Utils.COMMA_DELIMITER)[3].equals("\"" + country + "\"" ))
                .map(line -> {
                    List<String> parts = Arrays.asList(line.split(Utils.COMMA_DELIMITER, -1));
                    return Utils.getList(parts);
                });

        List<Map<String, Object>> airports = airportsRDD.collect().stream().flatMap(List::stream).collect(Collectors.toList());

        exchange.getIn().setBody(gson.toJson(airports));
    }


}
