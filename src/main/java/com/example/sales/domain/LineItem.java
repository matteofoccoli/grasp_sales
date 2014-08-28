package com.example.sales.domain;

/**
 * Created by matteo on 23/08/14.
 */
public class LineItem {

  private ProductDescription productDescription;
  private int quantity;

  public LineItem(ProductDescription productDescription, int quantity) {
    this.productDescription = productDescription;
    this.quantity = quantity;
  }

  public ProductDescription getProductDescription() {
    return productDescription;
  }

  public int getQuantity() {
    return quantity;
  }

  // By Information Expert
  public Double getSubTotal() {
    return getProductDescription().getPrice() * getQuantity();
  }
}
