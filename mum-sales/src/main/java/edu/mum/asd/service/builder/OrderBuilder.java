package edu.mum.asd.service.builder;

import edu.mum.asd.domain.Order;

/**
 * Created by gustavosousa on 4/19/17.
 */
public interface OrderBuilder {
    Order getOrder();

    void buildOrder();

}
