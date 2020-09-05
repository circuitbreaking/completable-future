package com.circuitbreaking.threads.summation;

import lombok.extern.slf4j.Slf4j;

import static com.circuitbreaking.threads.summation.function.IntFunctions.sumCubes;
import static com.circuitbreaking.threads.summation.function.IntFunctions.sumSquares;

@Slf4j
public class SquareCubeSummingThread {

  public static void sumSquareCube(int n) throws InterruptedException {

    int[] results = new int[2];

    Thread t1 = new Thread(() -> results[0] = sumSquares(n));
    Thread t2 = new Thread(() -> results[1] = sumCubes(n));

    t1.start();
    t2.start();
    t1.join();
    t2.join();

    log.info("Sum {}", results[0] + results[1]);
  }
}
