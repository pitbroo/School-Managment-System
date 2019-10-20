package admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import dbUtil.dbConnection;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField idToDelate;
    @FXML
    private DatePicker dob;
    @FXML
    TableView <StudentData> studenttable;
    @FXML
    TableColumn<StudentData, String> idcolumn;
    @FXML
    TableColumn<StudentData, String> firstnamecolumn;
    @FXML
    TableColumn<StudentData, String> lastnamecolumn;
    @FXML
    TableColumn<StudentData, String> emailcolumn;
    @FXML
    TableColumn<StudentData, String> dobcolumn;
    @FXML
    private  Button loadbutton;
    private ObservableList<StudentData> data;
    private dbConnection db;

    public AdminController() throws IOException {
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.db = new dbConnection();
    }
    @FXML
    public void loadStudentData(ActionEvent event) throws SQLException{
        try{
            Connection connection = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()){
                this.data.add(new StudentData( rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5 )));
            }

        }catch (SQLException e){
            System.err.println("Error"+e);
        }
        this.idcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("lastname"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("firstname"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<StudentData,String>("DOB"));
        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);

    }
    @FXML
    private void addStudent(ActionEvent event)
    {
        String sql = "INSERT INTO `students`(`id`, `fname`, `lname`, `email`, `DOB`) VALUES (? , ?, ?, ?, ?)";
        try
        {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.id.getText());
            stmt.setString(2, this.firstname.getText());
            stmt.setString(3, this.lastname.getText());
            stmt.setString(4, this.email.getText());
            stmt.setString(5, this.dob.getEditor().getText());
            stmt.setString(5, this.dob.getEditor().getText());
            stmt.execute();
            conn.close();
        }
        catch (SQLException e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());

        }
    }

    @FXML
    public void claerFields(ActionEvent event ) {
        this.id.setText("");
        this.firstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.setValue(null);
    }
    @FXML
    private void delateID(ActionEvent event)
    {
            String sql = "DELETE FROM students WHERE id=?";
        try
        {

            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.idToDelate.getText());
            stmt.execute();
            conn.close();
        }
        catch (SQLException e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }


}
