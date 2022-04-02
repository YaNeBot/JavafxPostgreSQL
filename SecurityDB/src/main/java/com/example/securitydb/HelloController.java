package com.example.securitydb;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    private Button exBtn;
    @FXML
    private PasswordField pasF;
    @FXML
    private TextField logF;
    @FXML
    private Label mistake;

    private static final String nameBd = "avecoder";
    private static final String userBd = "postgres";
    private static final String passwordBd = "Andri2001";
    private static final String urlBd = "jdbc:postgresql://localhost:5432/";



    @FXML
    public void onExit1(){
        Stage stage = (Stage) exBtn.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void onLogin() {
        connect(pasF.getText().toString(),logF.getText().toString());
    }

    public void connect (String pass, String log){
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(urlBd + nameBd, userBd, passwordBd);
            if(connection!=null) {
                System.out.println("Успешное подключение!");
                Statement statement = connection.createStatement();
                String current_tusk = "select * from newdata order by id desc limit 1";
                ResultSet result = statement.executeQuery(current_tusk);
                if(result.next()){
                    if(result.getString("Password").equals(pass)&&result.getString("Login").equals(log)){
                        NewWin.crNewWin("NewWin2");
                        onExit1();
                    }
                    else{mistake.setText("Неправильный логин или пароль!");}
                }
            }
            else{
                System.out.println("Нет подключения!");
            }
        }
        catch (Exception e){System.out.println(e);}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}