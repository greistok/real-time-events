package com.greistok.trading.realtimeevents.candel;

import com.greistok.trading.realtimeevents.model.Candel;
import com.greistok.trading.realtimeevents.model.EndFileIngestion;
import com.greistok.trading.realtimeevents.model.TickData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;

import java.util.Date;

public class CandelGenerator {

    private final int minutes;
    private Candel currentCandel;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public CandelGenerator(int minutes) {
        this.minutes = minutes;
    }

    @EventListener
    public void addTick(TickData tickData) {
        long milliSecondes = tickData.getDate().getTime();
        long rest = milliSecondes % (60000 * minutes);
        Date dateBase = new Date(milliSecondes - rest);
        Double bid = tickData.getBid();
        if(currentCandel == null || !currentCandel.getDate().equals(dateBase)) {
            if(currentCandel != null) {
                currentCandel.finish();
                applicationEventPublisher.publishEvent(currentCandel);
            }
            currentCandel = Candel.of(dateBase, minutes, bid);
        }
        currentCandel.update(tickData);
    }

    @EventListener
    public void finish(EndFileIngestion endFileIngestion) {
        currentCandel.finish();
        applicationEventPublisher.publishEvent(currentCandel);
        currentCandel = null;
    }
}
