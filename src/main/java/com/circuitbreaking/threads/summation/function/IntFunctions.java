package com.circuitbreaking.threads.summation.function;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class IntFunctions {

  public static int sumSquares(int n) {
    log.info("Calculating sum of squares...");
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException cause) {
      // Added to avoid checked Exception being thrown from method
      throw new RuntimeException(cause.getCause());
    }
    log.info("Finished calculating sum of squares...");
    return n * (n + 1) * (2 * n + 1) / 6;
  }

  public static int sumCubes(int n) {
    log.info("Calculating sum of cubes...");
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException cause) {
      // Added to avoid checked Exception being thrown from method
      throw new RuntimeException(cause.getCause());
    }
    log.info("Finished calculating sum of cubes...");
    return square(n) * square(n + 1) / 4;
  }

  public static int square(int n) {
    return n * n;
  }
}
