package edu.mum.asd.ui.controller;

import edu.mum.asd.framework.di.AutoInjected;
import edu.mum.asd.framework.di.BaseController;
import edu.mum.asd.framework.security.UserData;
import edu.mum.asd.framework.validation.ValidationResult;
import edu.mum.asd.framework.validation.annotation.NotNullValidation;
import edu.mum.asd.service.UserNamePwdAuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by gustavosousa on 4/13/17.
 */
public class LoginController extends BaseController{

    @AutoInjected
    private UserNamePwdAuthenticationService authenticationService;

    @FXML
    @NotNullValidation(message = "User name cannot be null.")
    private TextField userNameField;

    @FXML
    @NotNullValidation(message = "Password cannot be null.")
    private PasswordField passwordField;

    @FXML
    private Text message;

    @FXML
    public void loginAction(ActionEvent event){

        ValidationResult validationResult = this.validate();

        if(validationResult.valid()) {

            message.setText("");

            UserData userData = new UserData(this.userNameField.getText(), this.passwordField.getText());

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            authenticationService.setStage(stage);
            authenticationService.setMessage(message);

            authenticationService.authenticate(userData);
        } else {

            StringBuilder builder = new StringBuilder();

            validationResult.getErrors().forEach(error -> {
                builder.append(error.getMessage());
                builder.append(" ");
            });

            message.setText(builder.toString());
        }
    }
}
