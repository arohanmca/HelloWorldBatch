package com.example.batch.writer;

import org.springframework.batch.item.support.AbstractItemStreamItemWriter;

import java.util.List;

public class ConsoleItemWriter extends AbstractItemStreamItemWriter {

    @Override
    public void write(List items) throws Exception {
        System.out.println("****************writing each chunk*********************");
        items.stream().forEach(System.out::println);

    }
}
