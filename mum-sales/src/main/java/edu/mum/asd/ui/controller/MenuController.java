package edu.mum.asd.ui.controller;

import edu.mum.asd.service.UserNamePwdAuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class MenuController {


    @FXML
    void switchToNewPurchase(ActionEvent event) {

        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/order.fxml"));
            setPane(paneOne);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPane(AnchorPane paneOne) {
        BorderPane root = UserNamePwdAuthenticationService.getRoot();
        root.setCenter(paneOne);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.sizeToScene();
    }


    @FXML
    void switchToRegisterProduct(ActionEvent event) {

        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/products.fxml"));
            setPane(paneOne);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void switchToManageStock(ActionEvent event) {
        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/stock.fxml"));
            setPane(paneOne);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToRemoveProduct(ActionEvent actionEvent) {

        BorderPane root = UserNamePwdAuthenticationService.getRoot();

        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/removeProducts.fxml"));
            root.setCenter(paneOne);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToReport(ActionEvent actionEvent) {

        BorderPane root = UserNamePwdAuthenticationService.getRoot();

        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/produceReport.fxml"));
            root.setCenter(paneOne);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}