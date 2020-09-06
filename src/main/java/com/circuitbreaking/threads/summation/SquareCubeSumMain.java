package com.circuitbreaking.threads.summation;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

import static com.circuitbreaking.threads.summation.function.IntFunctions.sumCubes;
import static com.circuitbreaking.threads.summation.function.IntFunctions.sumSquares;

@Slf4j
public class SquareCubeSumMain {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    sumSquareCube(5);
    SquareCubeSummingThread.sumSquareCube(5);
    SquareCubeSummingExecutor.sumSquareCube(5);
    SquareCubeSummingCompletableFuture.sumSquareCube(5);
    SquareCubeSummingCompletableFutureTimeout.sumSquareCubeWithDefault(5);
  }

  static void sumSquareCube(int n) {
    log.info("Sum {}", sumSquares(n) + sumCubes(n));
  }
}
