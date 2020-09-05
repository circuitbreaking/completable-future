package com.circuitbreaking.threads.finance.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
public class StockService {

  public double getStockPrice(String symbol) {
    return getPrice(symbol);
  }

  private double getPrice(String symbol) {
    long startTime = System.currentTimeMillis();
    log.info("Getting Stock Price of {}", symbol);

    sleep();
    double value = ThreadLocalRandom.current().nextDouble(.0001, .001) * symbol.hashCode();
    double price = Math.floor(value * 100) / 100;

    log.info(
        "Stock {}, Price {}, Time Taken {}",
        symbol,
        price,
        (System.currentTimeMillis() - startTime));
    return price;
  }

  @SneakyThrows
  private void sleep() {
    TimeUnit.SECONDS.sleep(2);
  }
}
