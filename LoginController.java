package LoginApp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController  implements Initializable {
    LoginModel loginModel = new LoginModel();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label dbstatus;
    @FXML
    private ComboBox<option> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Połączony z Bazą Danych!");
        }
        else{
            this.dbstatus.setText("Nie połączono z Bazą Danych :<");
        }

        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML
    private void Login(ActionEvent event){
        //this.loginStatus.setText("Nie wybrano statusu");
        try
        {

            //if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), ((option)this.comboBox.getValue()).toString()))
           if (true)
            {
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();
                switch (((option)this.comboBox.getValue()).toString())
                {
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                }
            }
            else
            {
                this.loginStatus.setText("Błędny login lub hasło");
            }
        }
        catch (Exception localException) {}
    }
    public void studentLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader studentLoader = new FXMLLoader();
            Pane studentRoot = (Pane) studentLoader.load(getClass().getResource("/students/studentFXML.fxml").openStream());
            AdminController adminController = (AdminController)studentLoader.getController();
            Scene scene = new Scene(studentRoot);
            adminStage.setScene(scene);
            adminStage.setTitle("Student Panel");
            adminStage.setResizable(false);
            adminStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }
    public void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminRoot = (Pane) adminLoader.load(getClass().getResource("/admin/Admin.fxml").openStream());

            AdminController adminController = (AdminController)adminLoader.getController();
            Scene scene = new Scene(adminRoot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Panel");
            adminStage.setResizable(false);
            adminStage.show();
            adminStage.setOpacity(0.95);
            adminRoot.setStyle("-fx-background-color: #80ccff;");
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

}
