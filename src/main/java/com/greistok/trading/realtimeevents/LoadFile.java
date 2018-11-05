package com.greistok.trading.realtimeevents;

import com.greistok.trading.realtimeevents.ingest.HistDataComIngestor;
import com.greistok.trading.realtimeevents.model.Candel;
import com.greistok.trading.realtimeevents.repository.CandelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class LoadFile {

    @Value("D:\\DAT_ASCII_EURUSD_T_201810.csv")
    File file;
    @Autowired
    private HistDataComIngestor ingestor;
    @Autowired
    CandelRepository candelRepository;

    @GetMapping("/load")
    public void init() throws IOException {
        ingestor.ingestFile(file);
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        return String.valueOf(candelRepository.getCandels().values().size());
    }

    @GetMapping("/test2")
    public @ResponseBody String test2() {
        return String.valueOf(candelRepository.getCandels().values().size());
    }

    @EventListener
    public void print(Candel candel) {
        System.out.println(candel);
    }
}
