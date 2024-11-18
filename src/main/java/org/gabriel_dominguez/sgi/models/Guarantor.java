package org.gabriel_dominguez.sgi.models;

import java.util.List;

public class Guarantor {

  protected Integer id;

  protected String firstName;

  protected String lastName;

  protected String dni;

  protected String phone;

  protected Address address;


  public Guarantor(){}

  public Guarantor(Integer id, String firstName, String lastName, String dni, String phone, Address address) {
    this.id = id;
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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
