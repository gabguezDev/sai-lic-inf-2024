package org.gabriel_dominguez.sgi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PaymentsController {
  @FXML
  protected void goToHome(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/home.fxml"));
    Parent homeView = loader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(homeView);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  protected void goToPaymentsSectionAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/payments.fxml"));
    Parent ownersView = loader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(ownersView);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  protected void goToAddPayment(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/add-payment.fxml"));
    Parent homeView = loader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(homeView);
    stage.setScene(scene);
    stage.show();
  }
}