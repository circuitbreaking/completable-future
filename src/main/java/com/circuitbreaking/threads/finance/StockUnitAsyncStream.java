package com.circuitbreaking.threads.finance;

import com.circuitbreaking.threads.finance.service.CreditService;
import com.circuitbreaking.threads.finance.service.StockService;
import com.circuitbreaking.threads.finance.service.UsdEurConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Slf4j
public class StockUnitAsyncStream {

  public static void stockUnitsAsync() {
    Stream<CompletableFuture<Integer>> futureStockUnits = stockUnitStream();
    CompletableFuture<?>[] completableFutures =
        futureStockUnits
            .map(future -> future.thenAccept(System.out::println))
            .toArray(CompletableFuture[]::new);
    CompletableFuture.allOf(completableFutures).join();
  }

  private static Stream<CompletableFuture<Integer>> stockUnitStream() {
    StockService stockService = new StockService();
    List<String> stocks = List.of("INTC", "TSLA", "GOOG", "MSFT", "AAPL");

    return stocks.stream()
        .map(stock -> CompletableFuture.supplyAsync(() -> stockService.getStockPrice(stock)))
        .map(futute -> futute.thenApply(UsdEurConverter::convert))
        .map(
            future ->
                future.thenCompose(
                    eurPrice ->
                        CompletableFuture.supplyAsync(() -> CreditService.getUnit(eurPrice))));
  }
}
