package edu.mum.asd.service;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.exception.DatabaseException;
import edu.mum.asd.framework.security.UserData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Orgil on 4/18/2017.
 */
@InjectableComponent

public class ProductServiceImpl implements ProductService
{

    private Stage stage;

    private static BorderPane root;

    @DataAccess(collection = Product.class)
    private Repository repository;

public void addProduct(String name, String description, double price, int quantity)
    {
        Product prod=new Product();

        prod.setDescription(description);
        prod.setName(name);
        prod.setPrice(price);
        prod.setQuantity(quantity);

        try {
            repository.save(prod);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        QueryAdapter adapter = repository.createQueryAdapter();

        Optional<UserData> user = adapter
                .eq("name", prod.getName())
                .eq("description", prod.getDescription())
                .eq("quantity", prod.getQuantity())
                .eq("price", prod.getPrice())
                .findOne();

        System.out.println(user.isPresent());
    }
}