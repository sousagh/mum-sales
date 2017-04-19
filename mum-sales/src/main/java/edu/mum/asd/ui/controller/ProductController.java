package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.security.UserData;
import edu.mum.asd.service.ProductService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;
/*
 * Created by gustavosousa on 4/17/17.
 */
public class ProductController extends BaseController{

    @AutoInjected
    private ProductService productService;

    @DataAccess(collection = Product.class)
    private Repository repository;

    @FXML
    void test() {
        System.out.println("ProductController"+button7.getText());
    }

    @FXML
    private Label label;
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
    void add_function()
    {
        System.out.println("add product");

        System.out.println("Quantity: "+quantity.getText());
        System.out.println("Price: "+price.getText());
        System.out.println("new_product: "+new_product.getText());
        System.out.println("description: "+description.getText());

        QueryAdapter adapter = repository.createQueryAdapter();

        Optional<UserData> user = adapter
                .eq("name", new_product.getText())
                // .eq("description", prod.getDescription())
                // .eq("quantity", prod.getQuantity())
                // .eq("price", prod.getPrice())
                .findOne();

        System.out.println(user.isPresent());

        if(user.isPresent())
        {
            label.setText("Already present!");
            label.setVisible(true);
        }
        else
        {
            productService.addProduct(new_product.getText(), description.getText(), Integer.parseInt(price.getText()), Integer.parseInt(quantity.getText()));
            label.setText("Successfully added!");
            label.setVisible(true);
        }
    }
}