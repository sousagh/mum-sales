package edu.mum.asd.ui.util;

import edu.mum.asd.domain.Product;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by gustavosousa on 4/19/17.
 */
public class OrderTableEntry {

    private Product product;
    private SimpleStringProperty itemName;
    private SimpleStringProperty itemPrice;
    private SimpleStringProperty itemQuantity;

    public OrderTableEntry(String name, double price, Integer stock) {
        this.itemName = new SimpleStringProperty(name);
        this.itemPrice = new SimpleStringProperty(String.valueOf(price));
        this.itemQuantity = new SimpleStringProperty(stock.toString());
    }

    public OrderTableEntry(Product prod, Integer quantity) {
        this(prod.getName(), prod.getPrice(), quantity);
        this.product = prod;
    }

    public String getItemName() {
        return this.itemName.getValueSafe();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemPrice() {
        return this.itemPrice.getValueSafe();
    }

    public void setItemPrice(String itemName) {
        this.itemPrice.set(itemName);
    }

    public String getItemQuantity() {
        return this.itemQuantity.getValueSafe();
    }

    public void setItemQuantity(String itemName) {
        this.itemQuantity.set(itemName);
    }

    public Product getProduct() {
        return product;
    }
}
