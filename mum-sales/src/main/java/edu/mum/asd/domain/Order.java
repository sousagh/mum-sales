package edu.mum.asd.domain;

import edu.mum.asd.framework.domain.StorableEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by gustavosousa on 4/19/17.
 */
public class Order extends StorableEntity {

    private Date date;
    private List<OrderItem> items;
    private double total;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

}
