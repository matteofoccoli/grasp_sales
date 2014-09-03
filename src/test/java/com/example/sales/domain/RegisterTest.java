package com.example.sales.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by matteo on 26/08/14.
 */
public class RegisterTest {

  private ProductCatalog catalog;
  private Register register;
  private Store store;

  @Before
  public void setUp() throws Exception {
    catalog = mock(ProductCatalog.class);
    store = new Store();
    register = createARegisterStartingASale(store, catalog);
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
  public void asksCatalogForProductDetails() throws Exception {
    register.addSaleItem("12345", 12);

    verify(catalog).findProductDescriptionByCode("12345");
  }

  @Test
  public void endsCurrentSale() throws Exception {
    register.endSale();

    assertThat(register.getCurrentSale().isComplete(), is(true));
  }
  
  @Test
  public void makesPaymentForCurrentSale() throws Exception {
    Money cashTendered = new Money(12.5d);
    
    register.makePayment(cashTendered);
    
    assertThat(register.getCurrentSale().getPayment(), notNullValue());
  }
  
  @Test
  public void logsSaleAfterPayment() throws Exception {
    register.makePayment(new Money(12.23d));
   
    assertThat(store.getCompletedSales().size(), equalTo(1));  
  }

  private Register createARegisterStartingASale(Store store, ProductCatalog catalog) {
    Register register = new Register(store, catalog);
    register.makeNewSale();
    return register;
  }

}
