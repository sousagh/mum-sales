package edu.mum.asd.ui.controller;

import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.service.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/*
 * Created by gustavosousa on 4/17/17.
 */
public class ProductController extends BaseController{

    @AutoInjected
    private ProductService productService;

    @FXML
    void test() {
        System.out.println("ProductController"+button7.getText());
    }

    @FXML
    private Button button7;
    @FXML
    private Button add_product;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private TextField new_product;
    @FXML
    private TextField description;
    @FXML
    void add_function(){
        System.out.println("add product");

        System.out.println("Quantity: "+quantity.getText());
        System.out.println("Price: "+price.getText());
        System.out.println("new_product: "+new_product.getText());
        System.out.println("description: "+description.getText());

productService.addProduct(new_product.getText(), description.getText(), Integer.parseInt(price.getText()), Integer.parseInt(quantity.getText()));
    }
}