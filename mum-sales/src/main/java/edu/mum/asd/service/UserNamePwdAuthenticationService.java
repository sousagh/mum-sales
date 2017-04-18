package edu.mum.asd.service;

import edu.mum.asd.framework.data.DataAccess;
import edu.mum.asd.framework.data.Repository;
import edu.mum.asd.framework.data.read.QueryAdapter;
import edu.mum.asd.framework.di.InjectableComponent;
import edu.mum.asd.framework.security.UserData;
import edu.mum.asd.framework.security.UserNamePasswordAbstractAuthenticationService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by gustavosousa on 4/14/17.
 */
@InjectableComponent
public class UserNamePwdAuthenticationService extends UserNamePasswordAbstractAuthenticationService{

    private Stage stage;

    private static BorderPane root;

    private Text message;

    @DataAccess(collection = UserData.class)
    private Repository repository;

    @Override
    protected void onFailure() {
        message.setText("Authentication Failure.");
    }

    @Override
    protected void onSuccess() {

        try {

            root = new BorderPane();

            MenuBar bar = FXMLLoader.load( getClass().getResource("/menu.fxml") );
            AnchorPane paneOne = FXMLLoader.load(getClass().getResource("/home.fxml"));

            root.setTop(bar);
            root.setCenter(paneOne);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static BorderPane getRoot() {
        return root;
    }

    @Override
    protected boolean verifyUserData(UserData userData) {

        QueryAdapter adapter = repository.createQueryAdapter();

        Optional<UserData> user = adapter
                .eq("userName", userData.getUserName())
                .eq("password", userData.getPassword())
                .findOne();

        return user.isPresent();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMessage(Text message) {
        this.message = message;
    }
}
