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

    @Override
    public String findOrderByDate(Date startDate, Date endDate)
    {

        QueryAdapter adapter = orderRepository.createQueryAdapter();
        List<Order> orders = adapter
                .lt("date", endDate.getTime())
                .gt("date", startDate.getTime())
                .find();


        double globalTotal = 0;
        StringBuilder reporttext = new StringBuilder();
        for (Order order : orders) {

            double total = 0;

            reporttext.append("Order: " + order.getDate() + "\n");

            for (OrderItem orderItem : order.getItems()
                    ) {
                total += orderItem.getAmount() * orderItem.getProduct().getPrice();
                reporttext.append(orderItem.getProduct().getName() + " ->     " + orderItem.getAmount() + " x " + orderItem.getProduct().getPrice() + " = " + orderItem.getAmount() * orderItem.getProduct().getPrice() + "\n");
            }
            reporttext.append("\nTOTAL:   " + total + "\n\n");
            globalTotal += total;
        }
        reporttext.append("\n\n Global total: " + globalTotal);


        return reporttext.toString();

    }
}