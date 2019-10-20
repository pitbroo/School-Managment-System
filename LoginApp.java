package LoginApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.setStyle("-fx-background-color: #80ccff;");
        primaryStage.setTitle("Szkolny System Zarządzania");
        primaryStage.setScene(new Scene(root, 440 , 250));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOpacity(0.75);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
