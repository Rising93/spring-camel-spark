package com.it.spark.spring;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();
    }
    @Bean
    public SparkConf sparkConf() {
        return new SparkConf().setAppName("sparkCamel").setMaster("local[*]");
    }

    @Bean
    public org.apache.spark.api.java.JavaSparkContext sc() {
        return new org.apache.spark.api.java.JavaSparkContext(sparkConf());
    }

}
