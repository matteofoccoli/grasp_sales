package com.example.sales.domain;

/**
* Created by matteo on 26/08/14.
*/
public interface ProductCatalog {
  ProductDescription findProductDescriptionByCode(String code);
}
