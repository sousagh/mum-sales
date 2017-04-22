package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Order;
import edu.mum.asd.domain.OrderItem;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.service.OrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    private TextArea report;

    private String expectedPattern = "yyy-MM-dd";

    private SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

    @AutoInjected
    private OrderService orderService;

    private Date startDate, endDate;

    public void onClick(ActionEvent ae) {

        try {
            startDate = formatter.parse(start.getValue().toString());
            endDate = formatter.parse(end.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        report.setText(orderService.findOrderByDate(startDate, endDate));
    }
}