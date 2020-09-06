package com.circuitbreaking.threads.summation;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.circuitbreaking.threads.summation.function.IntFunctions.sumCubes;
import static com.circuitbreaking.threads.summation.function.IntFunctions.sumSquares;

/** Asynchronous computation example using CompletableFuture */
@Slf4j
public class SquareCubeSummingCompletableFutureTimeout {

  public static void sumSquareCube(int n) throws InterruptedException, ExecutionException {
    Integer sum =
        CompletableFuture.supplyAsync(() -> sumSquares(n))
            .thenCombine(CompletableFuture.supplyAsync(() -> sumCubes(n)), Integer::sum)
            .orTimeout(3, TimeUnit.SECONDS)
            .get();

    log.info("Sum {}", sum);
  }

  public static void sumSquareCubeWithDefault(int n)
      throws InterruptedException, ExecutionException {
    Integer sum =
        CompletableFuture.supplyAsync(() -> sumSquares(n))
            .thenCombine(CompletableFuture.supplyAsync(() -> sumCubes(n)), Integer::sum)
            .completeOnTimeout(0, 1, TimeUnit.SECONDS)
            .get();

    log.info("Sum {}", sum);
  }
}
