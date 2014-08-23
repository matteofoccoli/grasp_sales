package com.example.sales.domain;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SaleTest {

  @Test
  public void addsOneLineItemByCreatorGRASP() throws Exception {
    Sale s = new Sale(new Date());
    ProductDescription pd = new ProductDescription(12, "blah blah", 23.0);

    s.addLineItem(pd, 3);

    assertThat("wrong number of items", s.getLineItems().size(), equalTo(1));
    LineItem sli = s.getLineItems().get(0);
    assertThat("wrong quantity", sli.getQuantity(), equalTo(3));
    assertThat("wrong product description", sli.getProductDescription(), equalTo(pd));
  }

  @Test
  public void getSalesTotalByInformationExpertGRASP() throws Exception {
    Sale s = new Sale(new Date());

    s.addLineItem(new ProductDescription(1, "white bread", 1.0), 1);
    s.addLineItem(new ProductDescription(2, "butter", 0.5), 2);

    assertThat("wrong number of line items", s.getLineItems().size(), equalTo(2));
    assertThat("wrong total", s.getTotal(), equalTo(1.0 * 1 + 0.5 * 2));
  }
}