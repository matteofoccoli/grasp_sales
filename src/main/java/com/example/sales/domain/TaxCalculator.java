package com.example.sales.domain;

/**
 * Created by matteo on 05/09/14.
 */
public interface TaxCalculator {

  // By Polymorphism
  void getTaxes(Sale sale);

}
