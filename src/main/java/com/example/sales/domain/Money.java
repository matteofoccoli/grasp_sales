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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(amount);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other)
      return true;
    if (other == null)
      return false;
    if (getClass() != other.getClass())
      return false;
    if (Double.doubleToLongBits(amount) != Double
        .doubleToLongBits(((Money) other).amount))
      return false;
    return true;
  }

  public boolean lessThan(Money other) {
    if (getAmount() < other.getAmount())
      return true;
    return false;
  }
  
}
