package com.example.sales.domain;

import java.util.Date;

/**
 * Created by matteo on 26/08/14.
 */
// By Controller
public class Register {

  private final ProductCatalog catalog;
  private final Store store;
  private final TaxCalculator taxCalculator;
  private Sale currentSale;

  public Register(Store store, ProductCatalog catalog, TaxCalculator taxCalculator) {
    this.store = store;
    this.catalog = catalog;
    this.taxCalculator = taxCalculator;
  }

  // By Creator
  public void makeNewSale() {
    currentSale = new Sale(new Date());
  }

  public Sale getCurrentSale() {
    return currentSale;
  }

  // By Controller
  public void addSaleItem(long productId, int quantity) {
    // By Information Expert
    final ProductDescription description = catalog.findProductDescriptionById(productId);
    // By Creator
    getCurrentSale().addLineItem(description, quantity);
  }

  // By Controller
  public void endSale() {
    getCurrentSale().becomeComplete();
    // By Polymorphism
    // By Indirection
    // (both are mechanism to protect around variations)
    // By Protected Variations (instability/predicted variations)
    taxCalculator.getTaxes(getCurrentSale());
  }

  // By Controller
  public void makePayment(Money cashTendered) {
    // By Creator
    // By Low Coupling
    getCurrentSale().makePayment(cashTendered);
    // By Information Expert
    store.addCompletedSale(getCurrentSale());
  }
  
}
