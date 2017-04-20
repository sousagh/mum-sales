package edu.mum.asd.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 * Created by Orgil on 4/20/2017.
 */
public class ProduceReportController {
@FXML
private DatePicker start;
@FXML
private DatePicker end;
@FXML
private Button generate;
@FXML
private TextArea report;


    public void onChange1(ActionEvent actionEvent) {
        System.out.println(start.getValue());
    }

    public void onChange2(ActionEvent actionEvent) {
        System.out.println(end.getValue());
    }
}