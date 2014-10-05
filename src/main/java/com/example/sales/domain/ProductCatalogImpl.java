package com.example.sales.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by matteo on 05/10/14.
 */
public class ProductCatalogImpl implements ProductCatalog {
  private ProductAdapter adapter;
  private Map<Long, ProductDescription> products;

  public ProductCatalogImpl(ProductAdapter adapter) {
    this.adapter = adapter;
    this.products = new HashMap<Long, ProductDescription>();
  }

  @Override
  public ProductDescription findProductDescriptionById(long id) {
    ProductDescription product = products.get(id);
    if (product == null) {
      product = adapter.findProductDescriptionById(id);
      products.put(product.getId(), product);
    }
    return product;
  }
}
