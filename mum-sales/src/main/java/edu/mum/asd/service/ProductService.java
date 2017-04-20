package edu.mum.asd.service;

import edu.mum.asd.domain.Product;

import java.util.List;

/**
 * Created by Orgil on 4/18/2017.
 */
public interface ProductService {
    void addProduct(String name, String description, double price, int quantity);
    List<Product> findByName(String criterion);
    void updateProduct(Product product);
    void removeProduct(Product product);
}