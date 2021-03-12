package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;
import javafx.scene.control.Hyperlink;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessage;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Hyperlink hyperLink;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("images/bomasad.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("images/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);


    }

    public void loginButtonOnAction(ActionEvent event){

        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            validateLogin();
        }
        else{
            loginMessage.setText("Please enter username and password");
        }
    }

    public void hyperLinkOnAction(){
        Stage stage = (Stage) hyperLink.getScene().getWindow();
        stage.close();
        createAccountForm();
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameTextField.getText() + "' AND password ='" + passwordTextField.getText() + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    bookForm();
                }
                else{
                    loginMessage.setText("Invalid");
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }


    }

    public void createAccountForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 400));
            registerStage.show();

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void bookForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("crud.fxml"));
            Stage crudStage = new Stage();
            crudStage.initStyle(StageStyle.UNDECORATED);
            crudStage.setScene(new Scene(root, 645, 400));
            crudStage.show();

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
