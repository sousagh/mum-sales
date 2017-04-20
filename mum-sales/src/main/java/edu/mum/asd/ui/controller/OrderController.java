package edu.mum.asd.ui.controller;

import com.sun.xml.internal.ws.api.FeatureConstructor;
import edu.mum.asd.domain.Order;
import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.exception.DatabaseException;
import edu.mum.asd.service.OrderService;
import edu.mum.asd.service.ProductService;
import edu.mum.asd.service.builder.OrderBuilderImpl;
import edu.mum.asd.service.builder.OrderDirector;
import edu.mum.asd.ui.command.CommandInvoker;
import edu.mum.asd.ui.util.ApplicationConstants;
import edu.mum.asd.ui.util.OrderTableEntry;
import edu.mum.asd.ui.util.SearchTableEntry;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gustavosousa on 4/17/17.
 */
public class OrderController extends BaseController {

    @AutoInjected
    private OrderService orderService;

    @AutoInjected
    private ProductService productService;

    @FXML
    private Text errorMessage;

    @FXML
    private TextField searchText;

    @FXML
    private Text totalText;

    @FXML
    private Text successText;

    @FXML
    private TableView<SearchTableEntry> searchTable;

    @FXML
    private TableColumn<SearchTableEntry, String> searchNameCol;

    @FXML
    private TableView<OrderTableEntry> orderListTable;

    @FXML
    private TableColumn<OrderTableEntry, String> orderNameCol;

    @FXML
    private TableColumn<OrderTableEntry, String> orderPriceCol;

    @FXML
    private TableColumn<OrderTableEntry, String> orderQuantityCol;

    private ObservableList<SearchTableEntry> searchData;

    private ObservableList<OrderTableEntry> orderData;

    private CommandInvoker invoker;

    @FXML
    private void initialize(){
        this.orderNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        this.orderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        this.orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

        this.orderData = FXCollections.observableArrayList();
        this.orderListTable.setItems(this.orderData);

        invoker = new CommandInvoker(orderData, totalText);


        this.searchNameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        this.searchTable.setRowFactory(tv -> {
            TableRow<SearchTableEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    SearchTableEntry rowData = row.getItem();
                    successText.setText("");

                    if(!isAlreadyInList(rowData)){
                        ApplicationContext.getInstance().putExtraParam(ApplicationConstants.PRODUCT, rowData);
                        ApplicationContext.getInstance().putExtraParam(ApplicationConstants.INVOKER, invoker);
                        try {

                            Parent root =  FXMLLoader.load(getClass().getResource("/order-item.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Add Order Item");
                            stage.setScene(new Scene(root));

                            stage.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        errorMessage.setText("");
                    } else {
                        errorMessage.setText("Can't add the same product twice.");
                    }
                }
            });
            return row;
        });
        onSearch();
    }

    private boolean isAlreadyInList(SearchTableEntry rowData) {
        String itemName = rowData.getItemName();
       return orderData.stream().map(OrderTableEntry::getItemName)
               .filter(name -> itemName.equals(name)).findAny().isPresent();
    }

    @FXML
    public void undo(){
        successText.setText("");
        this.invoker.undo();
    }

    @FXML
    public void createOrder(){
        successText.setText("");
        if(orderData.isEmpty()){
            this.errorMessage.setText("No order data.");
        } else {
            this.errorMessage.setText("");
            try {
                /**
                 * Builder!
                 */
                OrderDirector orderDirector = new OrderDirector(new OrderBuilderImpl(orderData, Double.valueOf(totalText.getText())));
                orderDirector.constructOrder();

                Order order = orderDirector.getOrder();

                List<Product> products = this.orderData.stream().map(OrderTableEntry::getProduct).collect(Collectors.toList());

                orderService.placeOrder(order, products);

                successText.setText("Order successfully placed!");
                
                clear();

            } catch (Exception e) {
                e.printStackTrace();
                this.errorMessage.setText(e.getMessage());
            }
        }
    }

    private void clear() {
        this.errorMessage.setText("");
        this.searchText.setText("");
        this.onSearch();
        this.orderData = FXCollections.observableArrayList();
        this.orderListTable.setItems(this.orderData);
        invoker = new CommandInvoker(orderData, totalText);
        this.totalText.setText("0");
    }

    @FXML
    public void onSearch(){
        List<Product> products = productService.findByName(searchText.getText());
        List<SearchTableEntry> list = new ArrayList<>();
        products.forEach(prod-> {
            list.add(new SearchTableEntry(prod));
        });
        this.searchData = FXCollections.observableArrayList(list);
        this.searchTable.setItems(this.searchData);
    }

}