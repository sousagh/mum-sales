package edu.mum.asd.ui.command;

import edu.mum.asd.ui.util.OrderTableEntry;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

/**
 * Created by gustavosousa on 4/19/17.
 */
public interface Command {
    void execute();
    void undo();
    void setReceiver(ObservableList<OrderTableEntry> orderData, Text totalText);
}
