package com.example.sales.domain;

import java.util.Date;

/**
 * Created by matteo on 26/08/14.
 */
// By controller
public class Register {

  private final ProductCatalog catalog;
  private Sale currentSale;

  public Register(ProductCatalog catalog) {
    this.catalog = catalog;
  }

  // By Creator
  public void makeNewSale() {
    currentSale = new Sale(new Date());
  }

  public Sale getCurrentSale() {
    return currentSale;
  }

  public void addSaleItem(String productCode, int quantity) {
    // By Expert
    final ProductDescription description = catalog.findProductDescriptionByCode(productCode);
    // By Creator
    getCurrentSale().addLineItem(description, quantity);
  }
}
