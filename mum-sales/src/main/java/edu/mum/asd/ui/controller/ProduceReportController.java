package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Order;
import edu.mum.asd.domain.OrderItem;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.service.OrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Orgil on 4/20/2017.
 */
public class ProduceReportController extends BaseController {
@FXML
private DatePicker start;
@FXML
private DatePicker end;
//@FXML
//private Button generate;
@FXML
private TextArea report;
private String expectedPattern = "yyy-MM-dd";
SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

    @AutoInjected
    private OrderService orderService;

    @DataAccess(collection = Order.class)
    private Repository repository;

    private Date startDate, endDate;
    String startVal, endVal;

    public void onChange1(ActionEvent actionEvent) {

        System.out.println("string"+start.getValue().toString());

        try
        {
            // (2) give the formatter a String that matches the SimpleDateFormat pattern
            startDate = formatter.parse(start.getValue().toString());
            // (3) prints out "Tue Sep 22 00:00:00 EDT 2009"
            System.out.println(startDate);
        }
        catch (ParseException e)
        {
            // execution will come here if the String that is given
            // does not match the expected format.
            e.printStackTrace();
        }
    }

    public void onChange2(ActionEvent actionEvent) {

        System.out.println("string"+end.getValue().toString());

        try
        {
            // (2) give the formatter a String that matches the SimpleDateFormat pattern
            endDate = formatter.parse(end.getValue().toString());
            // (3) prints out "Tue Sep 22 00:00:00 EDT 2009"
            System.out.println(endDate);
        }
        catch (ParseException e)
        {
            // execution will come here if the String that is given
            // does not match the expected format.
            e.printStackTrace();
        }
    }

    private double total, global_total;
    private String reporttext;

    public void onClick(ActionEvent ae) {

        List<Order> orders=orderService.findOrderByDate(startDate, endDate);

        global_total=0;
        reporttext="";
        for (Order order:orders) {
            total=0;
            reporttext+="\n\nOrder: "+order.getDate()+"\n";
            System.out.println(reporttext);
            for (OrderItem orderItem: order.getItems()
                    ) {
                total+=orderItem.getAmount()*orderItem.getProduct().getPrice();
                reporttext+=orderItem.getProduct().getName()+"->     "+orderItem.getAmount()+" x "+orderItem.getProduct().getPrice()+" = "+orderItem.getAmount()*orderItem.getProduct().getPrice()+"\n";

            }
            reporttext+="\nTOTAL:   "+total;
            global_total+=total;
        }
        reporttext+="\n\n Global total: "+global_total;
        System.out.println("Global total: "+global_total);
        report.setText(reporttext);
    }
}