package com.circuitbreaking.threads.summation;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.circuitbreaking.threads.summation.function.IntFunctions.sumCubes;
import static com.circuitbreaking.threads.summation.function.IntFunctions.sumSquares;

@Slf4j
public class SquareCubeSummingExecutor {

  public static void sumSquareCube(int n) throws InterruptedException, ExecutionException {

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    Future<Integer> s1 = executorService.submit(() -> sumSquares(n));
    Future<Integer> s2 = executorService.submit(() -> sumCubes(n));

    log.info("Sum {}", s1.get() + s2.get());

    executorService.shutdown();
  }
}
