package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.sql.Connection;
import java.sql.Statement;

public class RegisterController {

    @FXML
    private Button closeBtn;
    @FXML
    private TextField usernametext;
    @FXML
    private TextField firstnametext;
    @FXML
    private TextField lastnametext;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private PasswordField setPasswordTextField;
    @FXML
    private Label labelAlert;
    @FXML
    private Label labelPassword;


    public void registerBtnOnAction(ActionEvent event){


        if (setPasswordTextField.getText().equals(confirmPasswordField.getText())){
            registerUser();
        }
        else{
            labelPassword.setText("Password does not match");
        }



    }

    public void closeBtnOnAction(ActionEvent event){
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser () {
        if (usernametext.getText().isEmpty() && firstnametext.getText().isEmpty() && lastnametext.getText().isEmpty() && setPasswordTextField.getText().isEmpty()) {

            labelAlert.setText("Please fill up");
        }else{
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String firstname = firstnametext.getText();
            String lastname = lastnametext.getText();
            String username = usernametext.getText();
            String password = confirmPasswordField.getText();



            String insertFields = "INSERT INTO user_account(lastname, firstname, username, password) VALUES('";
            String insertValues = firstname + "','" + lastname + "','" + username + "','" + password +"')";
            String insertToRegister = insertFields + insertValues;

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);
                labelAlert.setText("User registered successfully");
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }

        }



    }



}

