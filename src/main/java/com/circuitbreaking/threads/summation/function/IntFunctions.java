package com.circuitbreaking.threads.summation.function;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class IntFunctions {

  public static int sumSquares(int n) {
    log.info("Calculating sum of squares...");
    sleep(2);
    log.info("Finished calculating sum of squares...");
    return n * (n + 1) * (2 * n + 1) / 6;
  }

  public static int sumCubes(int n) {
    log.info("Calculating sum of cubes...");
    sleep(3);
    log.info("Finished calculating sum of cubes...");
    return square(n) * square(n + 1) / 4;
  }

  @SneakyThrows
  private static void sleep(int i) {
    TimeUnit.SECONDS.sleep(i);
  }

  public static int square(int n) {
    return n * n;
  }
}
