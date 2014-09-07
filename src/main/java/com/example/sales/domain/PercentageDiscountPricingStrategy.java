package com.example.sales.domain;

public class PercentageDiscountPricingStrategy implements PricingStrategy {

  private double percentage;

  public PercentageDiscountPricingStrategy(double percentage) {
    this.percentage = percentage;
  }

  @Override
  public Money getTotal(Sale sale) {
    return sale.getPreDiscountTotal().times(1 - percentage / 100);
  }

}