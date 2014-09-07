package com.example.sales.domain;

/**
 * Created by matteo on 23/08/14.
 */
public class SalesLineItem {
  private Sale sale;
  private ProductDescription productDescription;
  private int quantity;

  public SalesLineItem(Sale sale, ProductDescription productDescription, int quantity) {
    this.sale = sale;
    this.productDescription = productDescription;
    this.quantity = quantity;
  }

  public Sale getSale() {
    return sale;
  }

  public ProductDescription getProductDescription() {
    return productDescription;
  }

  public int getQuantity() {
    return quantity;
  }
}
