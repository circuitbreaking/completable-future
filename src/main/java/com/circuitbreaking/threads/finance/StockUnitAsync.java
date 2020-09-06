package com.circuitbreaking.threads.finance;

import com.circuitbreaking.threads.finance.service.CreditService;
import com.circuitbreaking.threads.finance.service.StockService;
import com.circuitbreaking.threads.finance.service.UsdEurConverter;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class StockUnitAsync {

  public static List<Integer> stockUnitsAsync() {
    StockService stockService = new StockService();
    List<String> stocks = List.of("INTC", "TSLA", "GOOG", "MSFT", "AAPL");

    List<CompletableFuture<Integer>> futureUnits =
        stocks.stream()
            .map(stock -> CompletableFuture.supplyAsync(() -> stockService.getStockPrice(stock)))
            .map(futute -> futute.thenApply(UsdEurConverter::convert))
            .map(
                future ->
                    future.thenCompose(
                        eurPrice ->
                            CompletableFuture.supplyAsync(() -> CreditService.getUnit(eurPrice))))
            .collect(Collectors.toList());

    return futureUnits.stream().map(CompletableFuture::join).collect(Collectors.toList());
  }
}
