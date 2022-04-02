package com.example.securitydb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class NewController implements Initializable {

    private static final String nameBd = "avecoder";
    private static final String userBd = "postgres";
    private static final String passwordBd = "Andri2001";
    private static final String urlBd = "jdbc:postgresql://localhost:5432/";

    @FXML
    private Button exBtn2, plusB;

    @FXML
    private TableView <Person> nTabl;

    @FXML
    private TableColumn<Person, String> namecol,name2nd,dolzhnost;

    @FXML
    private TextField names, names2, dolzhno;

    private ObservableList <Person> list = FXCollections.observableArrayList();


    Connection connection;
    Statement statement;
    String current_tusk;
    PreparedStatement preparedStatement;

    @FXML
    public void onExit2(){
        Stage stage = (Stage) exBtn2.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void delIt() throws SQLException {
        nTabl.getItems().removeAll(nTabl.getSelectionModel().getSelectedItems());
        System.out.println();
    }


    @FXML
    public void delAll() throws SQLException {
        current_tusk = "delete from newtable";
        preparedStatement = connection.prepareStatement(current_tusk);
        preparedStatement.executeUpdate();
        nTabl.getItems().removeAll(list);
    }

    @FXML
    public void plusAct() throws Exception {

        current_tusk = "insert into newtable (name, ndname, dolzhnost) values (?, ?, ?);";
        preparedStatement = connection.prepareStatement(current_tusk);
        preparedStatement.setString(1, names.getText());
        preparedStatement.setString(2, names2.getText());
        preparedStatement.setString(3, dolzhno.getText());
        preparedStatement.executeUpdate();
        forTabl2();
    }
    public void forTabl2() throws SQLException {
        String newCurrent_tusk = "select * from newtable offset ((select count(*) from newtable)-1)";
        ResultSet resultSet = statement.executeQuery(newCurrent_tusk);
        if(resultSet.next()) {
            list.add(new Person(resultSet.getString("name"), resultSet.getString("ndname"), resultSet.getString("dolzhnost")));
            namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            name2nd.setCellValueFactory(new PropertyValueFactory<>("Name2"));
            dolzhnost.setCellValueFactory(new PropertyValueFactory<>("Dolzh"));
        }

    }


    public void forTabl() throws SQLException {

        String newCurrent_tusk = "select name, ndname,dolzhnost from newtable";
        ResultSet resultSet = statement.executeQuery(newCurrent_tusk);
        while(resultSet.next()){
            list.add(new Person(resultSet.getString("name"), resultSet.getString("ndname"), resultSet.getString("dolzhnost")));
            namecol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            name2nd.setCellValueFactory(new PropertyValueFactory<>("Name2"));
            dolzhnost.setCellValueFactory(new PropertyValueFactory<>("Dolzh"));
        }
        nTabl.setItems(list);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(urlBd + nameBd, userBd, passwordBd);
            statement = connection.createStatement();
            if (connection != null) {
                System.out.println("Успешное подключение!");
                forTabl();
            }
        }
        catch(Exception e){}

    }
}
