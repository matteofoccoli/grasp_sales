package com.example.sales.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductAdapterImplTest {

  public static final int NOT_LOCAL_ID = 77;
  public static final int LOCAL_ID = 24;
  private ProductAdapter localAdapter, remoteAdapter, adapter;

  @Before
  public void setUp() throws Exception {
    localAdapter = mock(ProductAdapter.class);
    when(localAdapter.findProductDescriptionById(LOCAL_ID)).thenReturn(
        new ProductDescription(LOCAL_ID, "Foo", new Money(23)));
    remoteAdapter = mock(ProductAdapter.class);
    adapter = new ProductAdapterImpl(localAdapter, remoteAdapter);
  }

  @Test
  public void findsLocally() throws Exception {
    adapter.findProductDescriptionById(LOCAL_ID);

    verify(localAdapter).findProductDescriptionById(LOCAL_ID);
    verify(remoteAdapter, never()).findProductDescriptionById(LOCAL_ID);
  }

  @Test
  public void searchesRemoteIfNotFoundLocally() throws Exception {
    adapter.findProductDescriptionById(NOT_LOCAL_ID);

    verify(localAdapter).findProductDescriptionById(NOT_LOCAL_ID);
    verify(remoteAdapter).findProductDescriptionById(NOT_LOCAL_ID);
  }
}