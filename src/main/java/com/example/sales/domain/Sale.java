package com.example.sales.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by matteo on 23/08/14.
 */
public class Sale {
  
  private Date time;
  private List<LineItem> lineItems;
  private boolean complete;
  private Payment payment;

  public Sale(Date time) {
    this.time = time;
    this.lineItems = new ArrayList<>();
  }

  public Date getTime() {
    return time;
  }

  // by Creator: sale contains line-items
  // by Low Coupling: clients of sale do now know anything about line-item
  public void addLineItem(ProductDescription pd, int i) {
    LineItem lineItem = new LineItem(pd, i);
    lineItems.add(lineItem);
  }

  public List<LineItem> getLineItems() {
    return Collections.unmodifiableList(lineItems);
  }

  // By Information Expert: sale has all the information to calculate total
  public Money getTotal() {
    Money total = new Money(0);
    for (LineItem lineItem : lineItems) {
      total.add(lineItem.getSubtotal());
    }
    return total;
  }

  public boolean isComplete() {
    return complete;
  }

  // By Information Expert
  public void becomeComplete() {
    this.complete = true;
  }

  public Payment getPayment() {
    return payment;
  }

  // By Creator: sale contains payment!
  // By Low Coupling: avoid other objects to know about payment
  public void makePayment(Money cashTendered) {
    payment = new Payment(cashTendered);    
  }

  // By Information Expert: sale has all needed informations to calculate balance
  public Double getBalance() {
    return getTotal().getAmount() - getPayment().getAmount().getAmount();
  }
  
}
