package com.circuitbreaking.threads.finance.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CreditService {

  public static int getUnit(double eurPrice) {
    return stockUnit(eurPrice);
  }

  private static int stockUnit(double eurPrice) {
    long startTime = System.currentTimeMillis();
    log.info("Getting Stock unit for {}", eurPrice);

    sleep();
    Random random = new Random();
    int unit = random.ints(1, 20).findFirst().getAsInt();

    log.info(
        "Price {}, Unit {}, Time Taken {}",
        eurPrice,
        unit,
        (System.currentTimeMillis() - startTime));
    return unit;
  }

  @SneakyThrows
  private static void sleep() {
    TimeUnit.SECONDS.sleep(1);
  }
}
