package com.example.sales.domain;

import java.util.Date;

public class Fixtures {

  public static final ProductDescription BUTTER = new ProductDescription(2, "butter", new Money(0.5));
  public static final ProductDescription WHITE_BREAD =
      new ProductDescription(1, "white bread", new Money(1.0));
  public static final Date TODAY = new Date();
  
  public static Sale createSaleWithBreadAndButter() {
    Sale s = new Sale(TODAY);
    s.addLineItem(WHITE_BREAD, 1);
    s.addLineItem(BUTTER, 2);
    return s;
  }
  
}
