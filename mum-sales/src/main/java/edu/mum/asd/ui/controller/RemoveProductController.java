package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.service.ProductService;
import edu.mum.asd.ui.util.SearchTableEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Orgil on 4/19/2017.
 */
public class RemoveProductController extends BaseController {

    @AutoInjected
    private ProductService productService;

    @FXML
    private Label label;

    @FXML
    private TableView<SearchTableEntry> searchTable;

    public TextField searchProduct;
    @FXML
    private Button removeProduct;

    @FXML
    private TableColumn<SearchTableEntry, String> itemDescription;

    @FXML
    private TableColumn<SearchTableEntry, String> itemStock;

    @FXML
    private TableColumn<SearchTableEntry, String> itemName;

    private ObservableList<SearchTableEntry> data;

    @FXML
    public void onEnter(ActionEvent ae){

        System.out.println(searchProduct.getText());
        List<Product> products = productService.findByName(searchProduct.getText());

        List<SearchTableEntry> list = new ArrayList<>();

        for (Product a:products
                ) {
            System.out.println(a.getName());
        }

        products.forEach(prod-> {
            list.add(new SearchTableEntry(prod));
        });


        this.data = FXCollections.observableArrayList(list);
        this.searchTable.setItems(this.data);
    }

    @FXML
    public void initialize() {

        this.itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        this.itemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        this.itemStock.setCellValueFactory(new PropertyValueFactory<>("itemStock"));

        this.data = FXCollections.observableArrayList();
        this.searchTable.setItems(this.data);

        this.searchTable.setRowFactory(tv -> {
            TableRow<SearchTableEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    removeProduct.setDisable(false);
                }
            });
            return row;
        });
    }

    public void removeButton(ActionEvent actionEvent) {

        System.out.println("remove button is active");

        int selectedIndex = searchTable.getSelectionModel().getSelectedIndex();

        SearchTableEntry selectedRecord = (SearchTableEntry) searchTable.getItems().get(selectedIndex);

        productService.removeProduct(selectedRecord.getProduct());
        data.remove(selectedRecord);
    }
}