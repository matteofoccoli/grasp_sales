package com.example.sales.domain;

public class Payment {

  private Money amount;

  public Payment(Money amount) {
    super();
    this.amount = amount;
  }

  public Money getAmount() {
    return amount;
  }
  
}
