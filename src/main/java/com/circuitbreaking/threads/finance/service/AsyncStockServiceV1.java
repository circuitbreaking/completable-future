package com.circuitbreaking.threads.finance.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AsyncStockServiceV1 {

  public Future<Double> getAsyncStockPrice(String symbol) {
    CompletableFuture<Double> futureStockPrice = new CompletableFuture<>();
    new Thread(
            () -> {
              try {
                futureStockPrice.complete(getPrice(symbol));
              } catch (Exception cause) {
                futureStockPrice.completeExceptionally(cause);
              }
            })
        .start();

    return futureStockPrice;
  }

  private double getPrice(String symbol) throws Exception {
    return new StockService().getStockPrice(symbol);
  }
}
