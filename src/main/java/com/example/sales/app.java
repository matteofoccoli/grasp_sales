package com.example.sales;

import com.example.sales.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matteo on 13/09/14.
 */
public class app {

  private static BufferedReader reader;
  private static ProductCatalog catalog;
  private static TaxCalculator taxCalculator;
  private static Register register;
  private static Store store;

  public static void main(String args[]) throws Exception {

    reader = new BufferedReader(new InputStreamReader(System.in));
    initializeDomainObjects();

    welcome();
    printSeparator();

    startSale();
    askForItems();
    endSale();

    printReceipt();
  }

  private static void printReceipt() {
    printReceiptSeparator();
    List<LineItem> items = register.getCurrentSale().getLineItems();
    for (LineItem item : items) {
      printLineItemRow(item);
    }
    printTotal(register.getCurrentSale().getTotal().getAmount());
    printSeparator();
  }

  private static void printLineItemRow(LineItem item) {
    System.out.println(String.format("%d %s:\t%.2f",
        item.getQuantity(),
        item.getProductDescription().getDescription(),
        item.getProductDescription().getPrice().getAmount()));
  }

  private static void initializeDomainObjects() {
    initializeStoreCatalog();
    initializeTaxCalculator();
    initializeStoreAndRegister();
  }

  private static void endSale() {
    register.endSale();
  }

  private static void askForItems() throws IOException {
    for(;;) {
      String itemId = askForItemId();
      if (itemId.equalsIgnoreCase("finish"))
        break;
      int itemQty = askForItemQuantity(itemId);

      register.addSaleItem(Long.valueOf(itemId), itemQty);

      printSeparator();
    }
    printSeparator();
  }

  private static void startSale() {
    register.makeNewSale();
  }

  private static void initializeStoreAndRegister() {
    store = new Store();
    register = new Register(store, catalog, taxCalculator);
  }

  private static void initializeTaxCalculator() {
    taxCalculator = new EmptyTaxCalculator();
  }

  private static void initializeStoreCatalog() {
    List<ProductDescription> products = new ArrayList<>();
    products.add(new ProductDescription(1l, "Bread", new Money(2.0)));
    products.add(new ProductDescription(2l, "Butter", new Money(4.0)));
    catalog = new InMemoryCatalog(products);
  }

  private static void printTotal(double total) {
    System.out.println("");
    System.out.println("\tTotal:\t" + total);
  }

  private static void printSeparator() {
    System.out.println("***********************************");
  }

  private static void printReceiptSeparator() {
    System.out.println("\n********** Sale Receipt ***********");
  }

  private static void welcome() {
    System.out.println("Welcome to Store Application.");
    System.out.println("Starting a new sale...");
  }

  private static int askForItemQuantity(String itemId) throws IOException {
    System.out.print(String.format("Enter quantity for %s: ", itemId));
    return Integer.valueOf(reader.readLine());
  }

  private static String askForItemId() throws IOException {
    System.out.print("Enter item ID or type \"finish\": ");
    return reader.readLine();
  }

  private static class InMemoryCatalog implements ProductCatalog {
    private Map<Long, ProductDescription> products = new HashMap();

    private InMemoryCatalog(List<ProductDescription> products) {
      for (ProductDescription product : products) {
        this.products.put(product.getId(), product);
      }
    }

    @Override
    public ProductDescription findProductDescriptionById(long id) {
      return products.get(id);
    }
  }

  private static class EmptyTaxCalculator implements TaxCalculator {
    @Override
    public void getTaxes(Sale sale) {
    }
  }
}
