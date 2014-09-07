package com.example.sales.domain;

import static com.example.sales.domain.Fixtures.BUTTER;
import static com.example.sales.domain.Fixtures.TODAY;
import static com.example.sales.domain.Fixtures.createSaleWithBreadAndButter;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class SaleTest {

  @Test
  public void addsOneLineItemByCreatorGRASP() throws Exception {
    Sale s = new Sale(TODAY);
    ProductDescription pd = new ProductDescription(12, "blah blah",
        new Money(23.0));

    s.addLineItem(pd, 3);

    assertThat("wrong number of items", s.getLineItems().size(), equalTo(1));

    LineItem sli = s.getLineItems().get(0);
    assertThat("wrong quantity", sli.getQuantity(), equalTo(3));
    assertThat("wrong product description", sli.getProductDescription(), equalTo(pd));
  }

  @Test
  public void getTotalByInformationExpertGRASP() throws Exception {
    Sale s = createSaleWithBreadAndButter();

    assertThat("wrong number of line items", s.getLineItems().size(), equalTo(2));
    assertThat("wrong total", s.getTotal().getAmount(), equalTo(1.0 * 1 + 0.5 * 2));
  }
  
  @Test
  public void usePricingStrategyGettingTotal() throws Exception {
    Sale s = createSaleWithBreadAndButter();
    PricingStrategy ps = mock(PricingStrategy.class);
    s.setPricingStrategy(ps);
    
    s.getTotal();
    
    verify(ps).getTotal(eq(s));
  }

  @Test
  public void calculatesBalance() throws Exception {
    Sale s = new Sale(TODAY);
    s.addLineItem(BUTTER, 10);
    s.makePayment(new Money(1));
    
    assertThat(s.getBalance(), equalTo(4.0d));
  }
  
}