package com.circuitbreaking.threads.summation;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.circuitbreaking.threads.summation.function.IntFunctions.sumCubes;
import static com.circuitbreaking.threads.summation.function.IntFunctions.sumSquares;

/** Asynchronous computation example using CompletableFuture */
@Slf4j
public class SquareCubeSummingCompletableFuture {

  public static void sumSquareCube(int n) throws InterruptedException, ExecutionException {

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    CompletableFuture<Integer> s1 = new CompletableFuture<>();
    CompletableFuture<Integer> s2 = new CompletableFuture<>();
    CompletableFuture<Integer> result = s1.thenCombine(s2, Integer::sum);
    executorService.submit(() -> s1.complete(sumSquares(n)));
    executorService.submit(() -> s2.complete(sumCubes(n)));
    log.info("Sum {}", result.get());
    executorService.shutdown();
  }
}
