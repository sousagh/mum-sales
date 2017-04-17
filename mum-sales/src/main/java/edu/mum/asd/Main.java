package edu.mum.asd;

import edu.mum.asd.framework.ApplicationContext;
import edu.mum.asd.util.DBinit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Your sales App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ApplicationContext.initializeContext("edu.mum");
        DBinit.start();
        launch(args);
    }
}
