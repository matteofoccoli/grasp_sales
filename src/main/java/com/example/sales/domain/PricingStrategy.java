package com.example.sales.domain;

public interface PricingStrategy {

  // By Polymorphism
  Money getTotal(Sale sale);
  
}
