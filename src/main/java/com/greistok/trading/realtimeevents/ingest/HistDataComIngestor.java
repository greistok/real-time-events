package com.greistok.trading.realtimeevents.ingest;

import com.greistok.trading.realtimeevents.model.EndFileIngestion;
import com.greistok.trading.realtimeevents.model.TickData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class HistDataComIngestor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HHmmssSSS");

    public void ingestFile(File file) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(file));
        r.lines()
                .map(this::transform)
                .forEach(tickData -> applicationEventPublisher.publishEvent(tickData));
        applicationEventPublisher.publishEvent(new EndFileIngestion());
        r.close();
    }

    private TickData transform(String line) {
        String[] tmp = line.split(",");
        try {
            return TickData.of(dateFormat.parse(tmp[0]), Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
