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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by gustavosousa on 4/14/17.
 */
@InjectableComponent
public class UserNamePwdAuthenticationService extends UserNamePasswordAbstractAuthenticationService{

    private Stage stage;

    @DataAccess(collection = UserData.class)
    private Repository repository;

    @Override
    protected void onFailure() {
        System.out.println("Failure!");
    }

    @Override
    protected void onSuccess() {

        try {
            System.out.println("Success!");
            Parent root = FXMLLoader.load(getClass().getResource("/home.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

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
}
