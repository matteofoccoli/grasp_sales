package com.example.sales.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositePricingStrategy implements PricingStrategy {

  protected List<PricingStrategy> strategies;

  public CompositePricingStrategy() {
    super();
    strategies = new ArrayList<PricingStrategy>();
  }
  
  public void add(PricingStrategy strategy) {
    strategies.add(strategy);
  }
  
}