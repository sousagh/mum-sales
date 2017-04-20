package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.validation.ValidationResult;
import edu.mum.asd.framework.validation.annotation.NotNullValidation;
import edu.mum.asd.ui.command.AddOrderItemCommand;
import edu.mum.asd.ui.command.CommandInvoker;
import edu.mum.asd.ui.util.ApplicationConstants;
import edu.mum.asd.ui.util.SearchTableEntry;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by gustavosousa on 4/19/17.
 */
public class OrderItemController extends BaseController {
    @FXML
    private Text productNameText;

    @FXML
    private Text productPriceText;

    @FXML
    private Text quantityText;

    @FXML
    private Text messageText;

    @FXML
    @NotNullValidation(message = "Amount cannot be null")
    private TextField productAmount;

    private CommandInvoker invoker;

    private SearchTableEntry rowData;

    @FXML
    public void initialize(){

        rowData = (SearchTableEntry) ApplicationContext.getInstance().getExtraParam(ApplicationConstants.PRODUCT);
        invoker = (CommandInvoker) ApplicationContext.getInstance().getExtraParam(ApplicationConstants.INVOKER);
        productNameText.setText(rowData.getItemName());
        productPriceText.setText(String.valueOf(rowData.getProduct().getPrice()));
        quantityText.setText(String.valueOf(rowData.getProduct().getQuantity()));

        this.productAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    OrderItemController.this.productAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    @FXML
    public void onEnter(ActionEvent event){

        ValidationResult validationResult = this.validate();

        if(validationResult.valid()){
            Integer amount = Integer.valueOf(productAmount.getText());
            Product product = rowData.getProduct();

            if(amount > product.getQuantity()){
                messageText.setText("Not enough in stock");
            } else {
                invoker.execute(new AddOrderItemCommand(product, amount));
                messageText.setText("");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
            }
        } else {
            messageText.setText(validationResult.getErrors().get(0).getMessage());
        }




    }
}
