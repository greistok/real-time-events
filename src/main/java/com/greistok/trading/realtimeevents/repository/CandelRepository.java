package com.greistok.trading.realtimeevents.repository;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.greistok.trading.realtimeevents.model.Candel;
import lombok.Getter;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CandelRepository {

    @Getter
    Multimap<Integer, Candel> candels = ArrayListMultimap.create();

    @EventListener
    public void print(Candel candel) {
        candels.put(candel.getNbMinutes(), candel);
    }
}
