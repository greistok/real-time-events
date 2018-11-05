package com.greistok.trading.realtimeevents;

import com.greistok.trading.realtimeevents.ingest.HistDataComIngestor;
import com.greistok.trading.realtimeevents.model.Candel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class LoadFile {

    @Value("D:\\DAT_ASCII_EURUSD_T_201810.csv")
    File file;
    @Autowired
    private HistDataComIngestor ingestor;

    @EventListener
    public void init(ApplicationContextEvent event) throws IOException {
        ingestor.ingestFile(file);
    }

    @EventListener
    public void print(Candel candel) {
        System.out.println(candel);
    }
}
