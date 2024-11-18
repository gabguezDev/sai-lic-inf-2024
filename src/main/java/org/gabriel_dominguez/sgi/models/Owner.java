package org.gabriel_dominguez.sgi.models;

import java.util.List;

public class Owner {
  protected Integer id;

  protected String firstName;

  protected String lastName;

  protected String dni;

  protected String phone;

  protected Integer address; // AddressID

  private String fullAddress;

  protected List<Property> properties;

  protected List<Contract> contracts;

  public Owner(String firstName, String lastName, String dni, String phone) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dni = dni;
    this.phone = phone;
  }


  public Owner(Integer id, String firstName, String lastName, String dni, String phone, Integer address) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dni = dni;
    this.phone = phone;
    this.address = address;
  }

  public Owner(String firstName, String lastName, String dni, String phone, Integer address) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dni = dni;
    this.phone = phone;
    this.address = address;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getAddress() {
    return this.address;
  }

  public void setAddress(Integer address) {
    this.address = address;
  }

  public List<Property> getProperties() {
    return properties;
  }

  public void setProperties(List<Property> properties) {
    this.properties = properties;
  }

  public List<Contract> getContracts() {
    return contracts;
  }

  public void setContracts(List<Contract> contracts) {
    this.contracts = contracts;
  }

  public String getFullAddress() {
    return fullAddress;
  }

  // Método para concatenar la dirección y asignarla a fullAddress
  public void setFullAddress(Address address) {
    if (address != null) {
      this.fullAddress = String.format("%s, %s, %s, %s %s, Piso %s, Depto %s, %s",
          address.getCountry(),
          address.getProvince(),
          address.getCity(),
          address.getStreet(),
          address.getStreetNumber(),
          address.getFloor() != null ? address.getFloor() : "N/A",
          address.getApartmentNumber() != null ? address.getApartmentNumber() : "N/A",
          address.getIndications() != null ? address.getIndications() : ""
      );
    } else {
      this.fullAddress = "";
    }
  }


}
