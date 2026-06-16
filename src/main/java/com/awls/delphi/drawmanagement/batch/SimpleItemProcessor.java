package com.awls.delphi.drawmanagement.batch;

import org.springframework.batch.item.ItemProcessor;

public class SimpleItemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) {
        return item.toUpperCase();
    }
}

