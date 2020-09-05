package com.circuitbreaking.threads.finance.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
public class AsyncStockService {

  public Future<Double> getAsyncStockPrice(String symbol) {
    return CompletableFuture.supplyAsync(() -> getPrice(symbol));
  }

  private double getPrice(String symbol) {
    return new StockService().getStockPrice(symbol);
  }
}
