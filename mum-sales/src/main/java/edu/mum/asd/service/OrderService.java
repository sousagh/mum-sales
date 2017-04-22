package edu.mum.asd.service;

import edu.mum.asd.domain.Order;
import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.exception.DatabaseException;

import java.util.Date;
import java.util.List;

/**
 * Created by gustavosousa on 4/19/17.
 */
public interface OrderService {

    void placeOrder(Order order, List<Product> products) throws DatabaseException;
    List<Order> findOrderByDate(Date startDate, Date endDate);
}