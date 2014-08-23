package com.example.sales.domain;

/**
 * Created by matteo on 23/08/14.
 */
public class ProductDescription {
  private long id;
  private String description;
  private Double price;

  public ProductDescription(long id, String description, Double price) {
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

  public Double getPrice() {
    return price;
  }
}
