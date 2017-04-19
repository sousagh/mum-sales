package edu.mum.asd.ui.controller;

import edu.mum.asd.domain.Product;
import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.service.ProductService;
import edu.mum.asd.ui.util.ApplicationConstants;
import edu.mum.asd.ui.util.SearchTableEntry;
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
    private TextField updateText;

    private SearchTableEntry rowData;

    @AutoInjected
    private ProductService productService;

    @FXML
    public void initialize() {

        rowData = (SearchTableEntry) ApplicationContext.getInstance().getExtraParam(ApplicationConstants.PRODUCT);

        this.nameText.setText(rowData.getItemName());
        this.descriptionText.setText(rowData.getItemDescription());
        this.priceText.setText(String.valueOf(rowData.getProduct().getPrice()));
        this.stockText.setText(String.valueOf(rowData.getProduct().getQuantity()));
    }

    @FXML
    public void updateStock(){
        System.out.println("update!" + updateText.getText());
        Product product = rowData.getProduct();
        product.setQuantity(new Integer(updateText.getText()));
        productService.updateProduct(product);
    }
}
