package edu.mum.asd.service.builder;

import edu.mum.asd.domain.Order;

/**
 * Created by gustavosousa on 4/19/17.
 */
public class OrderDirector {

    private OrderBuilder orderBuilder;

    public OrderDirector(OrderBuilder orderBuilder) {
        this.orderBuilder = orderBuilder;
    }

    public void constructOrder() {
        orderBuilder.buildOrder();
    }

    public Order getOrder() {
        return orderBuilder.getOrder();
    }
}
