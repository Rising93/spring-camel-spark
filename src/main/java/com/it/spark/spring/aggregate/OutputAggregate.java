package com.it.spark.spring.aggregate;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class OutputAggregate implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

        JsonArray jsonArray = new JsonArray();
        if (oldExchange == null) {
            jsonArray.add(JsonParser.parseString(newExchange.getIn().getBody(String.class)).getAsJsonObject());
            newExchange.getIn().setBody(jsonArray);
            return newExchange;
        } else {
            jsonArray = oldExchange.getIn().getBody(JsonArray.class);
            jsonArray.add(JsonParser.parseString(newExchange.getIn().getBody(String.class)).getAsJsonObject());
            oldExchange.getIn().setBody(jsonArray);
            return oldExchange;
        }
    }
}
