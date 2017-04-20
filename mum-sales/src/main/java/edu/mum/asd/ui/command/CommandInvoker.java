package edu.mum.asd.ui.command;

import edu.mum.asd.ui.util.OrderTableEntry;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.util.Stack;

/**
 * Created by gustavosousa on 4/19/17.
 */
public class CommandInvoker {
    private final ObservableList<OrderTableEntry> orderData;

    private final Stack<Command> commands = new Stack<>();
    private final Text totalText;

    public CommandInvoker(ObservableList<OrderTableEntry> orderData, Text totalText) {
        this.orderData = orderData;
        this.totalText = totalText;
    }

    public void execute(Command command) {
        command.setReceiver(orderData, totalText);
        command.execute();
        commands.push(command);
    }

    public void undo(){
        if(!commands.isEmpty()){
            commands.pop().undo();
        }
    }

}
