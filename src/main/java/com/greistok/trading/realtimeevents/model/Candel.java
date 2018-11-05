package com.greistok.trading.realtimeevents.model;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
public class Candel {
    private final Date date;
    private final int nbMinutes;
    private final double ouverture;
    private double fermeture, max, min, moyenne;
    private int volume;

    private List<Double> bids = Lists.newArrayList();

    private Candel(Date date, int nbMinutes, double ouverture) {
        this.date = date;
        this.nbMinutes = nbMinutes;
        this.ouverture = ouverture;
        this.fermeture = ouverture;
        this.max = ouverture;
        this.min = ouverture;
    }

    public static Candel of(Date date, int nbMinutes, double ouverture) {
        return new Candel(date, nbMinutes, ouverture);
    }

    public void finish() {
        volume = bids.size();
        moyenne = bids.stream().mapToDouble(bid -> bid).average().getAsDouble();
    }

    public void update(TickData tickData) {
        Double bid = tickData.getBid();
        fermeture = bid;
        if(bid > max) max = bid;
        if(bid < min) min = bid;
        bids.add(bid);
    }
}
