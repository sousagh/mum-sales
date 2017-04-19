package edu.mum.asd.ui.util;

import edu.mum.asd.domain.Product;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by gustavosousa on 4/18/17.
 */
public class SearchTableEntry {
    private Product product;
    private SimpleStringProperty itemName;
    private SimpleStringProperty itemDescription;
    private SimpleStringProperty itemStock;

    public SearchTableEntry(String name, String description, Integer stock) {
        this.itemName = new SimpleStringProperty(name);
        this.itemDescription = new SimpleStringProperty(description);
        this.itemStock = new SimpleStringProperty(stock.toString());
    }

    public SearchTableEntry(Product prod) {
        this(prod.getName(), prod.getDescription(), prod.getQuantity());
        this.product = prod;
    }

    public String getItemName() {
        return this.itemName.getValueSafe();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemDescription() {
        return this.itemDescription.getValueSafe();
    }
    public void setItenDescription(String itemInfo) {
        this.itemDescription.set(itemInfo);
    }

    public String getItemStock() {
        return this.itemStock.getValueSafe();
    }

    public void setItemStock(String itemName) {
        this.itemStock.set(itemName);
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "SearchTableEntry{" +
                ", itemName=" + itemName +
                ", itemDescription=" + itemDescription +
                '}';
    }
}
