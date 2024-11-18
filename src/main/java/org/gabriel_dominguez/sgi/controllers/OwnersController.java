package org.gabriel_dominguez.sgi.controllers;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.gabriel_dominguez.sgi.models.Address;
import org.gabriel_dominguez.sgi.models.Owner;
import org.gabriel_dominguez.sgi.services.AddressesServices;
import org.gabriel_dominguez.sgi.services.OwnersServices;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OwnersController {
  @FXML
  private TextField firstName;
  @FXML
  private TextField lastName;
  @FXML
  private TextField dni;
  @FXML
  private TextField phone;
  @FXML
  private ComboBox<String> countryComboBox;
  @FXML
  private ComboBox<String> cityComboBox;
  @FXML
  private ComboBox<String> provinceComboBox;
  @FXML
  private TextField street;
  @FXML
  private TextField streetNumber;
  @FXML
  private TextField floor;
  @FXML
  private TextField apartmentNumber;
  @FXML
  private TextField indications;

  @FXML
  private Button saveButton;

  @FXML
  private Button editButton;

  @FXML
  private Button deleteButton;

  ///////////////////////////

  @FXML
  private TableView<Owner> ownersTableView;

  @FXML
  private TableColumn<Owner, Integer> idColumn;

  @FXML
  private TableColumn<Owner, String> firstNameColumn;

  @FXML
  private TableColumn<Owner, String> lastNameColumn;

  @FXML
  private TableColumn<Owner, String> dniColumn;

  @FXML
  private TableColumn<Owner, String> phoneColumn;

  @FXML
  private TableColumn<Owner, String> addressColumn;

  //////////////////////////////////////////////

  private OwnersServices ownerService;
  private AddressesServices addressesService;

  //////////////////////////////////////////////

  private Integer selectedOwnerId;


  public void onOwnerSave(ActionEvent event) {
    try {
      Address address = new Address(
          countryComboBox.getPromptText(),
          provinceComboBox.getPromptText(),
          cityComboBox.getPromptText(),
          street.getText(),
          streetNumber.getText(),
          floor.getText(),
          apartmentNumber.getText(),
          indications.getText()
      );

      Owner owner = new Owner(
          firstName.getText(),
          lastName.getText(),
          dni.getText(),
          phone.getText()
      );

      if (selectedOwnerId != null) {
        // Modo actualización
        owner.setId(selectedOwnerId);
        Owner existingOwner = ownerService.getById(selectedOwnerId);
        address.setId(existingOwner.getAddress());

        System.out.println("address = " + address);
        
        addressesService.update(address);
        ownerService.update(owner);
      } else {
        // Modo creación
        Integer newAddressId = addressesService.create(address);
        owner.setAddress(newAddressId);
        ownerService.create(owner);
      }

      // Regresar a la vista de la lista de owners
      goToOwnersSectionAction(event);

    } catch (Exception e) {
      // Manejar el error apropiadamente
      e.printStackTrace();
      // Mostrar mensaje de error al usuario
    }
  }

  @FXML
  public void onOwnerEdit(ActionEvent event) {
    try {
      goToAddOwner(event);
      //ownerService.update();
      //loadOwners();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void onOwnerDelete() {
    try {
      ownerService.delete(getSelectedOwnerId());
      loadOwners();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void initialize() {

    // Inicializar el servicio
    ownerService = new OwnersServices();
    addressesService = new AddressesServices();

    if (deleteButton != null && editButton != null) {
      deleteButton.setDisable(true);
      editButton.setDisable(true);
    }

    // Configurar las columnas
    setupColumns();

    // Cargar los datos
    loadOwners();

    //
    initializeSelectionListener();
  }

  private void setupColumns() {
    // Verificar que las columnas existan antes de configurarlas
    if (idColumn != null) {
      idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    }
    if (firstNameColumn != null) {
      firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    }
    if (lastNameColumn != null) {
      lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    }
    if (dniColumn != null) {
      dniColumn.setCellValueFactory(new PropertyValueFactory<>("dni"));
    }
    if (phoneColumn != null) {
      phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }
    if (addressColumn != null) {
      addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
  }

  private void loadOwners() {
    try {
      List<Owner> owners = ownerService.getAll();

      // Iterar sobre cada owner para obtener y concatenar la dirección
      for (Owner owner : owners) {
        if (owner.getAddress() != null) {
          Address address = addressesService.getById(owner.getAddress());
          if (address != null) {
            owner.setFullAddress(address);  // Crear el campo concatenado
          }
        }
        if (addressColumn != null) {
          addressColumn.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
        }
      }

      if (ownersTableView != null) {
        ownersTableView.setItems(FXCollections.observableArrayList(owners));
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Aquí podrías mostrar un diálogo de error al usuario
    }
  }

  private void initializeSelectionListener() {
    if (ownersTableView != null) {
      ownersTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
          selectedOwnerId = newSelection.getId();
          deleteButton.setDisable(false);  // Habilitar el botón de eliminar
          editButton.setDisable(false);    // Habilitar el botón de editar
        } else {
          selectedOwnerId = null;
          deleteButton.setDisable(true);   // Deshabilitar el botón de eliminar
          editButton.setDisable(true);     // Deshabilitar el botón de editar
        }
      });
    }
  }

  public Integer getSelectedOwnerId() {
    return selectedOwnerId != null ? selectedOwnerId : -1;
  }

  @FXML
  protected void goToOwnersSectionAction(ActionEvent event) throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/owners.fxml"));
      Parent ownersView = loader.load();

      Scene scene = new Scene(ownersView);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
      // Manejar el error apropiadamente
    }
  }

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
  protected void goToAddOwner(ActionEvent event) throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/add-owner.fxml"));
      Parent homeView = loader.load();

      // Obtener el controlador de la vista add-owner
      OwnersController addOwnerController = loader.getController();

      // Si estamos en modo edición, cargar los datos del owner seleccionado
      if (selectedOwnerId != null) {
        Owner selectedOwner = ownerService.getById(selectedOwnerId);
        if (selectedOwner != null && selectedOwner.getAddress() != null) {
          Address ownerAddress = addressesService.getById(selectedOwner.getAddress());
          addOwnerController.loadOwnerData(selectedOwner, ownerAddress);
        }
      }

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Scene scene = new Scene(homeView);
      stage.setScene(scene);
      stage.show();

    } catch (IOException e) {
      throw new IOException("Error al cargar la vista de edición", e);
    }
  }

  public void loadOwnerData(Owner owner, Address address) {
    Platform.runLater(() -> {
      // Datos del propietario
      firstName.setText(owner.getFirstName());
      lastName.setText(owner.getLastName());
      dni.setText(owner.getDni());
      phone.setText(owner.getPhone());

      // Datos de la dirección
      countryComboBox.setPromptText(address.getCountry());
      cityComboBox.setPromptText(address.getCity());
      provinceComboBox.setPromptText(address.getProvince());
      street.setText(address.getStreet());
      streetNumber.setText(address.getStreetNumber());
      floor.setText(address.getFloor());
      apartmentNumber.setText(address.getApartmentNumber());
      indications.setText(address.getIndications());

      // Guardar el ID del owner que se está editando
      selectedOwnerId = owner.getId();

      // Cambiar el texto del botón de guardar si es necesario
      if (saveButton != null) {
        saveButton.setText("Actualizar");
      }
    });
  }

}