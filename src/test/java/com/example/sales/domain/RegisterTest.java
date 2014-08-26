package com.example.sales.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by matteo on 26/08/14.
 */
public class RegisterTest {

  private ProductCatalog catalog;
  private Register register;

  @Before
  public void setUp() throws Exception {
    catalog = mock(ProductCatalog.class);
    register = createARegisterStartingASale(catalog);
  }

  @Test
  public void createsANewSale() throws Exception {
    assertThat(register.getCurrentSale(), notNullValue());
  }

  @Test
  public void addsAnItemToCurrentSale() throws Exception {
    register.addSaleItem("12345", 23);

    assertThat(register.getCurrentSale().getLineItems().size(), equalTo(1));
  }

  @Test
  public void askCatalogForProductDetails() throws Exception {
    register.addSaleItem("12345", 12);

    verify(catalog).findProductDescriptionByCode("12345");
  }

  private Register createARegisterStartingASale(ProductCatalog catalog) {
    Register register = new Register(catalog);
    register.makeNewSale();
    return register;
  }

}
