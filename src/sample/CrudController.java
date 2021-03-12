package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class CrudController implements Initializable {
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfPages;
    @FXML
    private TextField tfYear;
    @FXML
    private Button insBtn;
    @FXML
    private Button upBtn;
    @FXML
    private Button delBtn;
    @FXML
    private Button delallBtn;
    @FXML
    private TableView<Books> tvBooks;
    @FXML
    private TableColumn<Books, Integer> tvId;
    @FXML
    private TableColumn<Books, String> tvTitle;
    @FXML
    private TableColumn<Books, String> tvAuthor;
    @FXML
    private TableColumn<Books, Integer> tvPages;
    @FXML
    private TableColumn<Books, Integer> tvYear;
    @FXML
    private void handleButtonAction(ActionEvent e) {
        if(e.getSource() == insBtn){
            insertRecord();
        }else if(e.getSource() == upBtn) {
            updateRecord();
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle){
        showBooks();
    }


    public ObservableList<Books> getBooksList(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        String query = "SELECT * FROM books";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            Books books;
            while (queryResult.next()){
                books = new Books(queryResult.getInt("id"),queryResult.getString("title"),queryResult.getString("author"),queryResult.getInt("year"), queryResult.getInt("pages"));
                bookList.add(books);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return bookList;
    }

    public void showBooks(){
        ObservableList<Books> list = getBooksList();
        tvId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        tvTitle.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        tvAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        tvYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        tvPages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("pages"));

        tvBooks.setItems(list);


    }

    public void insertRecord(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "INSERT INTO books VALUES('" + tfID.getText() + "','" + tfTitle.getText() + "','" + tfAuthor.getText() + "','" + tfYear.getText() + "','" + tfPages.getText() + "')";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            showBooks();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    private void updateRecord(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "UPDATE books SET title='"+ tfTitle.getText() + "', author ='"+ tfAuthor.getText() + "', year ='"+ tfYear.getText() + "', pages = '" + tfPages.getText() + "' WHERE id= "+ tfID.getText();

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            showBooks();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    private void deleteRecord(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "UPDATE books SET title='"+ tfTitle.getText() + "', author ='"+ tfAuthor.getText() + "', year ='"+ tfYear.getText() + "', pages = '" + tfPages.getText() + "' WHERE id= "+ tfID.getText();

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);
            showBooks();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    }
