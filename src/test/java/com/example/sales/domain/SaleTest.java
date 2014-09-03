package com.example.sales.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.Test;

public class SaleTest {

  private static final ProductDescription BUTTER = new ProductDescription(2, "butter", new Money(0.5));
  private static final ProductDescription WHITE_BREAD =
      new ProductDescription(1, "white bread", new Money(1.0));
  private static final Date TODAY = new Date();


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
    Sale s = new Sale(TODAY);

    s.addLineItem(WHITE_BREAD, 1);
    s.addLineItem(BUTTER, 2);

    assertThat("wrong number of line items", s.getLineItems().size(), equalTo(2));
    assertThat("wrong total", s.getTotal().getAmount(), equalTo(1.0 * 1 + 0.5 * 2));
  }

  @Test
  public void calculatesBalance() throws Exception {
    Sale s = new Sale(TODAY);
    s.addLineItem(BUTTER, 10);
    s.makePayment(new Money(1));
    
    assertThat(s.getBalance(), equalTo(4.0d));
  }
}