package edu.mum.asd.ui.controller;

import edu.mum.asd.framework.ComponentTest;
import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 * Created by gustavosousa on 4/13/17.
 */
public class LoginController extends BaseController{

    @AutoInjected
    private ComponentTest componentTest;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginAction(ActionEvent event){
        System.out.println(this.userNameField.getText() + " - " + this.passwordField.getText());
        System.out.print(componentTest);
    }

}
