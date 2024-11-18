package org.gabriel_dominguez.sgi.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
  @FXML
  protected void goToPropertiesSectionAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/properties.fxml"));
    Parent propertiesView = loader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(propertiesView);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  protected void goToOwnersSectionAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/owners.fxml"));
    Parent ownersView = loader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(ownersView);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  protected void goToContractsSectionAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/contracts.fxml"));
    Parent contractsView = loader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(contractsView);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  protected void goToPaymentsSectionAction(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/payments.fxml"));
    Parent contractsView = loader.load();

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    Scene scene = new Scene(contractsView);
    stage.setScene(scene);
    stage.show();
  }

  public void goToHome(ActionEvent actionEvent) {
  }
}