package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args)
    {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            root.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: blue;");
            Scene scene=new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Handling Media Errors");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}