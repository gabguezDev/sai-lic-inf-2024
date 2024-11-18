package org.gabriel_dominguez.sgi.controllers;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.util.StringConverter;

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
import org.gabriel_dominguez.sgi.models.Property;
import org.gabriel_dominguez.sgi.services.AddressesServices;
import org.gabriel_dominguez.sgi.services.OwnersServices;
import org.gabriel_dominguez.sgi.services.PropertiesService;

import java.io.IOException;
import java.util.List;

public class PropertiesController {
  @FXML
  private ComboBox<String> tipoInmuebleComboBox;

  @FXML
  private TextField numHabsTextField;

  @FXML
  private TextField numBathsTextField;

  @FXML
  private CheckBox hasYardCheckBox;

  @FXML
  private CheckBox hasBalconyCheckBox;

  @FXML
  private ComboBox<String> countryComboBox;

  @FXML
  private ComboBox<String> cityComboBox;

  @FXML
  private ComboBox<String> provinceComboBox;

  @FXML
  private ComboBox<Owner> ownerComboBox;

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

  /////////////////////////

  @FXML
  private TableView<Property> propertiesTableView;

  @FXML
  private TableColumn<Property, Integer> idColumn;

  @FXML
  private TableColumn<Property, String> typeColumn;

  @FXML
  private TableColumn<Property, String> addressColumn;

  @FXML
  private TableColumn<Property, String> ownerColumn;

  @FXML
  private TableColumn<Property, String> roomsColumn;

  @FXML
  private TableColumn<Property, String> bathsColumn;

  @FXML
  private TableColumn<Property, Boolean> hasYardColumn;

  @FXML
  private TableColumn<Property, Boolean> hasBalconyColumn;

  /////////////////////////


  @FXML
  private Button saveButton;

  @FXML
  private Button editButton;

  @FXML
  private Button deleteButton;


  /////////////////////////

  private Integer selectedPropertyId;

  private OwnersServices ownersService;
  private AddressesServices addressesService;
  private PropertiesService propertiesService;

  public void onPropertySave(ActionEvent event) {
    try {
      // Crear el objeto Address con los datos del formulario
      Address address = new Address(
          countryComboBox.getValue(),
          provinceComboBox.getValue(),
          cityComboBox.getValue(),
          street.getText(),
          streetNumber.getText(),
          floor.getText(),
          apartmentNumber.getText(),
          indications.getText()
      );

      // Crear el objeto Property con los datos del formulario
      Property property = new Property(
          tipoInmuebleComboBox.getValue(),
          Integer.parseInt(numHabsTextField.getText()),
          Integer.parseInt(numBathsTextField.getText()),
          hasYardCheckBox.isSelected(),
          hasBalconyCheckBox.isSelected()
      );

      // Obtener el propietario seleccionado del ComboBox
      Owner selectedOwner = ownerComboBox.getValue();
      if (selectedOwner != null) {
        // Asignar el ID del propietario a la propiedad
        property.setOwner(selectedOwner.getId());
      }

      if (selectedPropertyId != null) {
        // Modo actualización
        property.setId(selectedPropertyId);
        Property existingProperty = propertiesService.getById(selectedPropertyId);
        address.setId(existingProperty.getAddress());

        addressesService.update(address);
        propertiesService.update(property);
      } else {
        // Modo creación
        Integer newAddressId = addressesService.create(address);
        property.setAddress(newAddressId);
        propertiesService.create(property);
      }

      // Regresar a la vista de la lista de propiedades
      goToPropertiesSectionAction(event);

    } catch (Exception e) {
      // Manejar el error apropiadamente
      e.printStackTrace();
      // Mostrar mensaje de error al usuario
    }
  }

  @FXML
  public void onPropertyEdit(ActionEvent event) {
    try {
      goToAddProperty(event);
      //ownerService.update();
      //loadOwners();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void onPropertyDelete() {
    try {
      propertiesService.delete(getSelectedPropertyId());
      loadProperties();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  public void initialize() {
    // Inicializar los servicios
    propertiesService = new PropertiesService();
    addressesService = new AddressesServices();
    ownersService = new OwnersServices();

    // Configurar el ComboBox para propietarios
    try {
      List<Owner> owners = ownersService.getAll();
      if (ownerComboBox != null) {
        ownerComboBox.setItems(FXCollections.observableArrayList(owners));

        // Usar el StringConverter correcto de javafx.util
        ownerComboBox.setConverter(new StringConverter<Owner>() {
          @Override
          public String toString(Owner owner) {
            return owner != null ? owner.getFirstName() + " " + owner.getLastName() : "";
          }

          @Override
          public Owner fromString(String string) {
            return null;
          }
        });
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (deleteButton != null && editButton != null) {
      deleteButton.setDisable(true);
      editButton.setDisable(true);
    }

    setupColumns();
    loadProperties();
    initializeSelectionListener();
  }


  private void setupColumns() {
    // Verificar que las columnas existan antes de configurarlas
    if (idColumn != null) {
      idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    }
    if (typeColumn != null) {
      typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    if (addressColumn != null) {
      addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
    if (ownerColumn != null) {
      ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
    }

    if (ownerColumn != null) {
      ownerColumn.setCellValueFactory(cellData -> {
        Integer ownerId = cellData.getValue().getOwner(); // Asegúrate de que getOwner() devuelva un objeto Owner
        if (ownerId != null) {
          Owner owner = ownersService.getById(ownerId);
          String fullName = owner.getFirstName() + " " + owner.getLastName();
          return new ReadOnlyStringWrapper(fullName);
        } else {
          return new ReadOnlyStringWrapper("Sin propietario"); // Texto para valores nulos
        }
      });
    }



    if (roomsColumn != null) {
      roomsColumn.setCellValueFactory(new PropertyValueFactory<>("rooms"));
    }
    if (bathsColumn != null) {
      bathsColumn.setCellValueFactory(new PropertyValueFactory<>("baths"));
    }

    if (hasYardColumn != null) {
      hasYardColumn.setCellValueFactory(new PropertyValueFactory<>("hasYard"));
      hasYardColumn.setCellFactory(column -> new TableCell<Property, Boolean>() {
        @Override
        protected void updateItem(Boolean item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(item ? "Sí" : "No");
          }
        }
      });
    }

    if (hasBalconyColumn != null) {
      hasBalconyColumn.setCellValueFactory(new PropertyValueFactory<>("hasBalcony"));
      hasBalconyColumn.setCellFactory(column -> new TableCell<Property, Boolean>() {
        @Override
        protected void updateItem(Boolean item, boolean empty) {
          super.updateItem(item, empty);
          if (empty || item == null) {
            setText(null);
          } else {
            setText(item ? "Sí" : "No");
          }
        }
      });
    }

  }

  private void loadProperties() {
    try {
      List<Property> properties = propertiesService.getAll();

      // Iterar sobre cada owner para obtener y concatenar la dirección
      for (Property property : properties) {
        if (property.getAddress() != null) {
          Address address = addressesService.getById(property.getAddress());
          if (address != null) {
            property.setFullAddress(address);  // Crear el campo concatenado
          }
        }
        if (addressColumn != null) {
          addressColumn.setCellValueFactory(new PropertyValueFactory<>("fullAddress"));
        }
      }

      if (propertiesTableView != null) {
        propertiesTableView.setItems(FXCollections.observableArrayList(properties));
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Aquí podrías mostrar un diálogo de error al usuario
    }
  }

  private void initializeSelectionListener() {
    if (propertiesTableView != null) {
      propertiesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
          selectedPropertyId = newSelection.getId();
          deleteButton.setDisable(false);  // Habilitar el botón de eliminar
          editButton.setDisable(false);    // Habilitar el botón de editar
        } else {
          selectedPropertyId = null;
          deleteButton.setDisable(true);   // Deshabilitar el botón de eliminar
          editButton.setDisable(true);     // Deshabilitar el botón de editar
        }
      });
    }
  }

  public Integer getSelectedPropertyId() {
    return selectedPropertyId != null ? selectedPropertyId : -1;
  }

  @FXML
  protected void goToPropertiesSectionAction(ActionEvent event) throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/properties.fxml"));
      Parent propertiesView = loader.load();

      Scene scene = new Scene(propertiesView);
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
  protected void goToAddProperty(ActionEvent event) throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/gabriel_dominguez/sgi/views/add-property.fxml"));
      Parent homeView = loader.load();

      // Obtener el controlador de la vista add-owner
      PropertiesController addPropertyController = loader.getController();

      // Si estamos en modo edición, cargar los datos del owner seleccionado
      if (selectedPropertyId != null) {
        Property selectedProperty = propertiesService.getById(selectedPropertyId);
        if (selectedProperty != null && selectedProperty.getAddress() != null) {
          Address ownerAddress = addressesService.getById(selectedProperty.getAddress());
          addPropertyController.loadPropertyData(selectedProperty, ownerAddress);
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

  public void loadPropertyData(Property property, Address address) {
    Platform.runLater(() -> {
      // Datos de la propiedad
      tipoInmuebleComboBox.setPromptText(property.getType());
      numHabsTextField.setText(String.valueOf(property.getRooms()));
      numBathsTextField.setText(String.valueOf(property.getBaths()));
      hasYardCheckBox.setText(String.valueOf(property.getHasYard()));
      hasBalconyCheckBox.setText(String.valueOf(property));

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
      selectedPropertyId = property.getId();

      // Cambiar el texto del botón de guardar si es necesario
      if (saveButton != null) {
        saveButton.setText("Actualizar");
      }
    });
  }
}