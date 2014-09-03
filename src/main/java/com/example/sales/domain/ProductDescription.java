package com.example.sales.domain;

/**
 * Created by matteo on 23/08/14.
 */
public class ProductDescription {
  private long id;
  private String description;
  private Money price;

  public ProductDescription(long id, String description, Money price) {
    this.id = id;
    this.description = description;
    this.price = price;
  }

  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public Money getPrice() {
    return price;
  }
}
