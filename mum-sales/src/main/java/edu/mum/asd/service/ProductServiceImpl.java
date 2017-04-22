package edu.mum.asd.service;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.exception.DatabaseException;
import edu.mum.asd.framework.security.UserData;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.List;
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

    public boolean addProduct(String name, String description, double price, int quantity)
        {
            QueryAdapter adapter = repository.createQueryAdapter();

            Optional<UserData> user = adapter
                    .eq("name", name) .findOne();

            if(user.isPresent()){
                return false;
            }

            System.out.println(user.isPresent());

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

            return true;
        }

    public void removeProduct(Product product)
    {
            repository.remove(product);
            System.out.println("end of removeProduct!!");
    }

    @Override
    public List<Product> findByName(String criterion) {

        QueryAdapter adapter = repository.createQueryAdapter();

        return adapter.regex("name", criterion).find();
    }

    @Override
    public void updateProduct(Product product) {
        try {
            repository.save(product);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}