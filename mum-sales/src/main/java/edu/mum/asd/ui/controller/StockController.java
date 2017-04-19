package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.service.ProductService;
import edu.mum.asd.ui.util.ApplicationConstants;
import edu.mum.asd.ui.util.SearchTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by gustavosousa on 4/17/17.
 */
public class StockController extends BaseController {

    @AutoInjected
    private ProductService productService;

    @FXML
    private TextField searchProduct;

    @FXML
    private TableView<SearchTableEntry> searchTable;

    @FXML
    private TableColumn<SearchTableEntry, String> itemDescriptionCol;

    @FXML
    private TableColumn<SearchTableEntry, String> itemStockCol;

    @FXML
    private TableColumn<SearchTableEntry, String> itemNameCol;

    private ObservableList<SearchTableEntry> data;

    @FXML
    public void onEnter(ActionEvent ae){
        System.out.println(searchProduct.getText()) ;
        List<Product> products = productService.findByName(searchProduct.getText());

        List<SearchTableEntry> list = new ArrayList<>();

        products.forEach(prod-> {
            list.add(new SearchTableEntry(prod));
        });


        this.data = FXCollections.observableArrayList(list);
        this.searchTable.setItems(this.data);
    }

    @FXML
    public void initialize() {

        this.itemNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        this.itemDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        this.itemStockCol.setCellValueFactory(new PropertyValueFactory<>("itemStock"));

        this.data = FXCollections.observableArrayList();
        this.searchTable.setItems(this.data);

        this.searchTable.setRowFactory(tv -> {
            TableRow<SearchTableEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    SearchTableEntry rowData = row.getItem();

                    ApplicationContext.getInstance().putExtraParam(ApplicationConstants.PRODUCT, rowData);
                    try {

                        Parent root =  FXMLLoader.load(getClass().getResource("/stock-popup.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Details");
                        stage.setScene(new Scene(root));
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });

    }
}
