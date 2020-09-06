package com.circuitbreaking.threads.finance;

import com.circuitbreaking.threads.finance.service.CreditService;
import com.circuitbreaking.threads.finance.service.StockService;
import com.circuitbreaking.threads.finance.service.UsdEurConverter;

import java.util.List;
import java.util.stream.Collectors;

public class StockUnitSync {
    public static List<Integer> stockUnits() {
        StockService stockService = new StockService();
        List<String> stocks = List.of("INTC", "TSLA", "GOOG", "MSFT", "AAPL");
        return stocks.stream()
                .map(stockService::getStockPrice)
                .map(UsdEurConverter::convert)
                .map(CreditService::getUnit)
                .collect(Collectors.toList());
    }
}