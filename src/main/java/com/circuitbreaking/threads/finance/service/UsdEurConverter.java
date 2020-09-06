package com.circuitbreaking.threads.finance.service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsdEurConverter {

  public static double convert(double price) {
    return price * .84;
  }
}
