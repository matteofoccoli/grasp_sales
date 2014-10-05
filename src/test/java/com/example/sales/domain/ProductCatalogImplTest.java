package com.example.sales.domain;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ProductCatalogImplTest {

  private ProductAdapter adapter;
  private ProductCatalog catalog;

  @Before
  public void setUp() throws Exception {
    adapter = mock(ProductAdapter.class);
    catalog = new ProductCatalogImpl(adapter);
  }

  @Test
  public void getsFromAdapter() throws Exception {
    catalog.findProductDescriptionById(23);

    verify(adapter).findProductDescriptionById(23);
  }

  @Test
  public void cachesAdapterResponseLocally() throws Exception {
    when(adapter.findProductDescriptionById(anyLong())).thenReturn(new ProductDescription(23, "Foo", new Money(12)));

    catalog.findProductDescriptionById(23);
    catalog.findProductDescriptionById(23);

    verify(adapter, times(1)).findProductDescriptionById(23);
  }
}