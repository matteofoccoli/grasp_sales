package com.example.sales.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class PricingStrategiesTest {

  private Sale sale;
  
  @Before
  public void setup() {
    sale = Fixtures.createSaleWithBreadAndButter();
  }
  
  @Test
  public void discountsByPercentage() throws Exception {
    PricingStrategy ps = new PercentageDiscountPricingStrategy(10);
    
    assertThat(ps.getTotal(sale).getAmount(), equalTo(sale.getPreDiscountTotal().times(0.9).getAmount()));
  }
  
  @Test
  public void resolvesConflictsApplyingBestForCustomerPolicy() throws Exception {
    CompositePricingStrategy ps = new CompositeBestForCustomerPricingStrategy();
    
    ps.add(new PercentageDiscountPricingStrategy(10));
    ps.add(new PercentageDiscountPricingStrategy(20));
    
    assertThat(ps.getTotal(sale), equalTo(sale.getTotal().times(0.8)));
  }
}
