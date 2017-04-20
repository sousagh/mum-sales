package edu.mum.asd.service;

import edu.mum.asd.domain.Order;
import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.exception.DatabaseException;

import java.util.List;

/**
 * Created by gustavosousa on 4/19/17.
 */
@InjectableComponent
public class OrderServiceImpl implements OrderService{

    @DataAccess(collection = Order.class)
    Repository orderRepository;

    @DataAccess(collection = Product.class)
    Repository productRepository;

    @Override
    public void placeOrder(Order order, List<Product> products) throws DatabaseException {

        order.getItems().stream().forEach(orderItem -> {
            for(Product p : products){
                if(p.getName().equals(orderItem.getProduct().getName())){
                    p.setQuantity(p.getQuantity() - orderItem.getAmount());
                    break;
                }
            }
        });

        orderRepository.save(order);

        for(Product p : products){
            productRepository.save(p);
        }
    }
}
