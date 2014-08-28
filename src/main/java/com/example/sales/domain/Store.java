package com.example.sales.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Store {
  List<Sale> completedSales;

  public Store() {
    super();
    completedSales = new ArrayList<Sale>();
  }

  public List<Sale> getCompletedSales() {
    return Collections.unmodifiableList(completedSales);
  }

  // By Information Expert
  public void addCompletedSale(Sale currentSale) {
    completedSales.add(currentSale);
  }
  
}
