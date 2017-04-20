package edu.mum.asd.service.builder;

import edu.mum.asd.domain.Order;
import edu.mum.asd.domain.OrderItem;
import edu.mum.asd.domain.Product;
import edu.mum.asd.ui.util.OrderTableEntry;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gustavosousa on 4/19/17.
 */
public class OrderBuilderImpl implements OrderBuilder{

    private final Double total;
    private Order order;
    private ObservableList<OrderTableEntry> orderData;

    public OrderBuilderImpl(ObservableList<OrderTableEntry> orderData, Double aDouble) {
        this.orderData = orderData;
        this.total = aDouble;
    }

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public void buildOrder() {
        order = new Order();
        order.setDate(new Date());
        order.setTotal(total);

        List<OrderItem> items = orderData.stream().map(entry ->{
            OrderItem item = new OrderItem((Product) entry.getProduct().clone(), Integer.valueOf(entry.getItemQuantity()));
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);

    }
}
