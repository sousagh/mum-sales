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

        BorderPane root = UserNamePwdAuthenticationService.getRoot();

        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/order.fxml"));
            root.setCenter(paneOne);

            System.out.println("switchToNewPurchase");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void switchToRegisterProduct(ActionEvent event) {

        BorderPane root = UserNamePwdAuthenticationService.getRoot();

        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/products.fxml"));
            root.setCenter(paneOne);

            System.out.println("switchToRegisterProduct");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void switchToManageStock(ActionEvent event) {
        BorderPane root = UserNamePwdAuthenticationService.getRoot();

        try {
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/stock.fxml"));
            root.setCenter(paneOne);

            System.out.println("switchToManageStock");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
