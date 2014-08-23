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

  public Sale(Date time) {
    this.time = time;
    this.lineItems = new ArrayList<>();
  }

  public Date getTime() {
    return time;
  }

  // by Creator
  public void addLineItem(ProductDescription pd, int i) {
    lineItems.add(new LineItem(this, pd, i));
  }

  public List<LineItem> getLineItems() {
    return Collections.unmodifiableList(lineItems);
  }

  // By Information Expert
  public Double getTotal() {
    Double total = 0d;
    for (LineItem lineItem : lineItems) {
      total += lineItem.getSubTotal();
    }
    return total;
  }
}
