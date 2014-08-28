package com.example.sales.domain;

import java.util.Date;

/**
 * Created by matteo on 26/08/14.
 */
// By controller
public class Register {

  private final ProductCatalog catalog;
  private final Store store;
  private Sale currentSale;

  public Register(Store store, ProductCatalog catalog) {
    this.store = store;
    this.catalog = catalog;
  }

  // By Creator
  public void makeNewSale() {
    currentSale = new Sale(new Date());
  }

  public Sale getCurrentSale() {
    return currentSale;
  }

  // By Controller
  public void addSaleItem(String productCode, int quantity) {
    // By Information Expert
    final ProductDescription description = catalog.findProductDescriptionByCode(productCode);
    // By Creator
    getCurrentSale().addLineItem(description, quantity);
  }

  // By Controller
  public void endSale() {
    getCurrentSale().becomeComplete();
  }

  // By Controller
  public void makePayment(double cashTendered) {
    // By Creator
    // By Low Coupling
    getCurrentSale().makePayment(cashTendered);
    // By Information Expert
    store.addCompletedSale(getCurrentSale());
  }
  
}
