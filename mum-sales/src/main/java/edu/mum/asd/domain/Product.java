package edu.mum.asd.domain;

import edu.mum.asd.framework.domain.StorableEntity;
import org.bson.types.ObjectId;

/**
 * Created by Orgil on 4/18/2017.
 */
public class Product extends StorableEntity{

    private int quantity;
    private double price;
    private String name;
    private String description;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}