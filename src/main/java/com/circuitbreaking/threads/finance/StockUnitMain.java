package com.circuitbreaking.threads.finance;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StockUnitMain {
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    List<Integer> stocksMap = StockUnitAsync.stockUnitsAsync();
    StockUnitAsyncStream.stockUnitsAsync();
    log.info("Time Taken {} ms, Stocks {}", (System.currentTimeMillis() - startTime), stocksMap);
  }

}
