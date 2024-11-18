package org.gabriel_dominguez.sgi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.gabriel_dominguez.sgi.utils.MariaDBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("views/home.fxml"));
    Scene scene = new Scene(fxmlLoader.load());

    stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/light-rent-100.png"))));

    stage.setTitle("Sistema de Gestión de Inmuebles");
    stage.resizableProperty().setValue(false);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    Connection dbConnection = MariaDBConnection.getConnection();

    System.out.println("dbConnection = " + dbConnection);


    launch();
  }
}