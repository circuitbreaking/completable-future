package com.circuitbreaking.threads.finance;

import com.circuitbreaking.threads.finance.service.AsyncStockService;
import com.circuitbreaking.threads.finance.service.StockService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
public class StockPriceMain {

  public static void main(String[] args)  {
    long startTime = System.currentTimeMillis();
    List<Double> stocksMap = stockPricesFuture();
    log.info("Time Taken {} ms, Stocks {}", (System.currentTimeMillis() - startTime), stocksMap);
  }

  public static List<Double> stockPrices() {
    StockService stockService = new StockService();
    List<String> stocks = List.of("INTC", "TSLA", "GOOG", "MSFT", "AAPL");
    return stocks.stream().map(stockService::getStockPrice).collect(Collectors.toList());
  }

  public static List<Double> stockPricesParallel() {
    StockService stockService = new StockService();
    List<String> stocks = List.of("INTC", "TSLA", "GOOG", "MSFT", "AAPL");
    return stocks.parallelStream().map(stockService::getStockPrice).collect(Collectors.toList());
  }

  public static List<Double> stockPricesFuture() {
    StockService stockService = new StockService();
    List<String> stocks = List.of("INTC", "TSLA", "GOOG", "MSFT", "AAPL");
    List<CompletableFuture<Double>> stockPriceFuture =
        stocks.stream()
            .map(stock -> CompletableFuture.supplyAsync(() -> stockService.getStockPrice(stock)))
            .collect(Collectors.toList());
    return stockPriceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
  }

  private static void callAsync() {
    AsyncStockService asyncStockService = new AsyncStockService();
    String symbol = "TSLA";
    Future<Double> price = asyncStockService.getAsyncStockPrice(symbol);
    doSomeThingElse();
    log.info("Price of {}, is {}", symbol, price);
  }

  @SneakyThrows
  private static void doSomeThingElse() {
    TimeUnit.SECONDS.sleep(5);
  }
}
