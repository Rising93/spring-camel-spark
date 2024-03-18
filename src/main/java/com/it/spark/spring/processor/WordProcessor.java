package com.it.spark.spring.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws IOException {
        MultipartFile[] file = exchange.getIn().getBody(MultipartFile[].class);
        List<String> words = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            InputStream inputStream = multipartFile.getInputStream();
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String word = scanner.hasNext() ? scanner.next() : "";
            words.add(word);
        }
        exchange.getIn().setBody(words);

    }


}
