package com.example.sales.domain;

public class CompositeBestForCustomerPricingStrategy extends
    CompositePricingStrategy {

  @Override
  public Money getTotal(Sale sale) {
    Money preDiscountTotal = sale.getPreDiscountTotal();
    Money lowest = new Money(preDiscountTotal.getAmount());
    Money temp;
    for(PricingStrategy strategy : strategies) {
      temp = strategy.getTotal(sale);
      lowest = min(temp, lowest);
    }
    return lowest;
  }
  
  private Money min(Money a, Money b) {
    if (a.lessThan(b))
      return a;
    return b;
  }

}