package com.greistok.trading.realtimeevents.controller;

import com.google.common.collect.Lists;
import com.greistok.trading.realtimeevents.model.Candel;
import com.greistok.trading.realtimeevents.repository.CandelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/history/candel")
public class CandelHistoryController {

    @Autowired
    CandelRepository candelRepository;

    @GetMapping("/{symbol}")
    public @ResponseBody List<Candel> getCandels(@PathVariable("symbol") String symbol) {
        return candelRepository.getCandels().get(1).stream().limit(10000).collect(Collectors.toList());
    }
}
