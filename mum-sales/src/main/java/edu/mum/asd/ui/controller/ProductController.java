package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.security.UserData;
import edu.mum.asd.service.ProductService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

/*
 * Created by gustavosousa on 4/17/17.
 */
public class ProductController extends BaseController {

    @AutoInjected
    private ProductService productService;

    @FXML
    private Label errorMessage;
    @FXML
    private Label successMessage;

    @FXML
    private TextField quantity;

    @FXML
    private TextField price;

    @FXML
    private TextField new_product;

    @FXML
    private TextField description;

    @FXML
    private void initialize(){
        this.quantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    ProductController.this.quantity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        this.price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    ProductController.this.price.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    public void addFunction() {

        boolean success = productService.addProduct(new_product.getText(), description.getText(), Integer.parseInt(price.getText()), Integer.parseInt(quantity.getText()));

        if (!success) {
            errorMessage.setText("Already present!");
            errorMessage.setVisible(true);
            successMessage.setVisible(false);
        } else {
            productService.addProduct(new_product.getText(), description.getText(), Integer.parseInt(price.getText()), Integer.parseInt(quantity.getText()));

            errorMessage.setVisible(false);
            successMessage.setText("Successfully added!");
            successMessage.setVisible(true);
            new_product.setText("");
            description.setText("");
            price.setText("");
            quantity.setText("");
        }
    }
}