package edu.mum.asd.ui.controller;

import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.security.UserData;
import edu.mum.asd.service.UserNamePwdAuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Created by gustavosousa on 4/13/17.
 */
public class LoginController extends BaseController{

    @AutoInjected
    private UserNamePwdAuthenticationService authenticationService;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginAction(ActionEvent event){
        System.out.println(this.userNameField.getText() + " - " + this.passwordField.getText());
        UserData userData = new UserData(this.userNameField.getText() , this.passwordField.getText());

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        authenticationService.setStage(stage);

        authenticationService.authenticate(userData);
    }

}
