package com.greistok.trading.realtimeevents.model;

import lombok.Data;

import java.util.Date;

@Data(staticConstructor = "of")
public class TickData {
    final Date date;
    final Double ask, bid;
}
