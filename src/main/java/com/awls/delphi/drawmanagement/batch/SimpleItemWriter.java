package com.awls.delphi.drawmanagement.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SimpleItemWriter implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> items) {
        items.forEach(System.out::println);
    }
}

