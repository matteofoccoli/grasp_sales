package com.example.sales.domain;

public interface PricingStrategy {

  Money getTotal(Sale sale);
  
}
