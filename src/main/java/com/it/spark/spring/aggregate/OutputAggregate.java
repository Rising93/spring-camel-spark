package com.it.spark.spring.aggregate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.it.spark.spring.utils.ProcessingService;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class OutputAggregate implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        JsonArray jsonArray = new JsonArray();
        Gson gson = ProcessingService.getGson();
        if (oldExchange == null) {
            jsonArray.add(JsonParser.parseString(newExchange.getIn().getBody(String.class)).getAsJsonObject());
            newExchange.getIn().setBody(gson.toJson(jsonArray));
            return newExchange;
        } else {
            jsonArray = oldExchange.getIn().getBody(JsonArray.class);
            jsonArray.add(JsonParser.parseString(newExchange.getIn().getBody(String.class)).getAsJsonObject());
            oldExchange.getIn().setBody(gson.toJson(jsonArray));
            return oldExchange;
        }
    }
}
