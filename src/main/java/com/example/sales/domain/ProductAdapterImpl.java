package com.example.sales.domain;

/**
 * Created by matteo on 05/10/14.
 */
public class ProductAdapterImpl implements ProductAdapter {

  private ProductAdapter local, remote;

  public ProductAdapterImpl(ProductAdapter local, ProductAdapter remote) {
    this.local = local;
    this.remote = remote;
  }

  @Override
  public ProductDescription findProductDescriptionById(long id) {
    ProductDescription description = local.findProductDescriptionById(id);
    if (description == null)
      description = remote.findProductDescriptionById(id);
    return description;
  }
}
