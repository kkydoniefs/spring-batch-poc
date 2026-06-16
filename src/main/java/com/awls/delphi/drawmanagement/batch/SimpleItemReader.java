package com.awls.delphi.drawmanagement.batch;

import org.springframework.batch.item.ItemReader;

public class SimpleItemReader implements ItemReader<String> {
    private final String[] data = {"Alice", "Bob", "Charlie", "Diana"};
    private int index = 0;

    @Override
    public String read() {
        if (index < data.length) {
            return data[index++];
        }
        return null;
    }
}

