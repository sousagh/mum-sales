package edu.mum.asd.service;

import edu.mum.asd.domain.Order;
import edu.mum.asd.domain.OrderItem;
import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.exception.DatabaseException;
import edu.mum.asd.framework.data.read.QueryAdapter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
   // List<Order> orders=null;
    @Override
    public List<Order> findOrderByDate(Date startDate, Date endDate)
    {
        //QueryAdapter adapter = orderRepository.createQueryAdapter();
       /* Optional<Order> orders = adapter
                .lt("date", startDate)
                .gt("date", endDate)
                .find();*/

        QueryAdapter adapter = orderRepository.createQueryAdapter();
//System.out.print("66666666666666666666666666666"+adapter.lt("date", startDate).gt("date", endDate).find());
        return adapter
                .lt("date", endDate)
                .gt("date", startDate)
                .find();

        //.regex("name", criterion).find();

        //orders=adapter.find();

       /* for (Order order: orders
             ) {
            if(order.getDate().compareTo(startDate)>=0 && order.getDate().compareTo(endDate)<=0)
                System.out.println("ooooooo"+order.getDate());
        }*/
//somefunction(date, date, orders)
        //return orders;
        //return null;
    }
}