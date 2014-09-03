package com.example.sales.domain;

/**
 * Created by matteo on 03/09/14.
 */
public class Money {
  private double amount;

  public Money(double amount) {
    this.amount = amount;
  }

  public double getAmount() {
    return amount;
  }

  public Money times(double times) {
    return new Money(this.getAmount() * times);
  }

  public void add(Money other) {
    amount += other.getAmount();
  }
}
