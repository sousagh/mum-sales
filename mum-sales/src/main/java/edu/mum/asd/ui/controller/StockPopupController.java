package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.validation.ValidationResult;
import edu.mum.asd.framework.validation.annotation.NotNullValidation;
import edu.mum.asd.service.ProductService;
import edu.mum.asd.ui.util.ApplicationConstants;
import edu.mum.asd.ui.util.SearchTableEntry;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * Created by gustavosousa on 4/18/17.
 */
public class StockPopupController extends BaseController{

    @FXML
    private Text nameText;

    @FXML
    private Text descriptionText;

    @FXML
    private Text priceText;

    @FXML
    private Text stockText;

    @FXML
    @NotNullValidation(message = "New stock cannot be null.")
    private TextField updateText;

    @FXML
    private Text successMessage;

    @FXML
    private Text errorMessage;

    private SearchTableEntry rowData;

    @AutoInjected
    private ProductService productService;

    private StockController parentController;

    @FXML
    public void initialize() {

        rowData = (SearchTableEntry) ApplicationContext.getInstance().getExtraParam(ApplicationConstants.PRODUCT);

        this.nameText.setText(rowData.getItemName());
        this.descriptionText.setText(rowData.getItemDescription());
        this.priceText.setText(String.valueOf(rowData.getProduct().getPrice()));
        this.stockText.setText(String.valueOf(rowData.getProduct().getQuantity()));

        this.updateText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    StockPopupController.this.updateText.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    public void updateStock(){
        ValidationResult validate = this.validate();
        if(validate.valid()){
            try {
                Product product = rowData.getProduct();
                product.setQuantity(new Integer(updateText.getText()));
                productService.updateProduct(product);
                successMessage.setVisible(true);
                errorMessage.setVisible(false);

            } catch (Exception e) {
                successMessage.setVisible(false);
                errorMessage.setText("Error!");
                errorMessage.setVisible(true);
            }
        } else {
            errorMessage.setVisible(true);
            successMessage.setVisible(false);
            errorMessage.setText(validate.getErrors().get(0).getMessage());
        }

    }

    public void setParentController(StockController parentController) {
        this.parentController = parentController;
    }
}
