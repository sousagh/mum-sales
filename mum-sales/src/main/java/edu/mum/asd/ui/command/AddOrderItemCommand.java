package edu.mum.asd.ui.command;

import edu.mum.asd.domain.Product;
import edu.mum.asd.ui.util.OrderTableEntry;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

/**
 * Created by gustavosousa on 4/19/17.
 */
public class AddOrderItemCommand implements Command{

    private Product product;
    private Integer amount;
    private ObservableList<OrderTableEntry> table;
    private OrderTableEntry row;
    private Text totalText;

    public AddOrderItemCommand(Product product, Integer amount) {
        this.product = product;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.row = new OrderTableEntry(product, amount);
        String text = totalText.getText();

        double subtotal = product.getPrice() * amount;

        double aDouble = Double.valueOf(text);

        totalText.setText(String.valueOf(subtotal + aDouble));

        table.add(this.row);
    }

    @Override
    public void undo() {
        table.remove(row);

        String text = totalText.getText();
        double subtotal = product.getPrice() * amount;

        double aDouble = Double.valueOf(text);

        totalText.setText(String.valueOf( aDouble - subtotal));
    }

    @Override
    public void setReceiver(ObservableList<OrderTableEntry> orderData, Text totalText) {
        this.table = orderData;
        this.totalText = totalText;
    }

}
