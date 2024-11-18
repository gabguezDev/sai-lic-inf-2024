package org.gabriel_dominguez.sgi.models;

public class Address {

  protected Integer id;

  private String country;

  private String city;

  private String province;

  private String street;

  private String streetNumber;

  private String floor;

  private String apartmentNumber;

  private String indications;

  public Address() {
  }

  public Address(String country, String province, String city, String street, String streetNumber, String floor, String apartmentNumber, String indications) {
    this.country = country;
    this.province = province;
    this.city = city;
    this.street = street;
    this.streetNumber = streetNumber;
    this.floor = floor;
    this.apartmentNumber = apartmentNumber;
    this.indications = indications;
  }

  public Address(Integer id, String country, String province, String city, String street, String streetNumber, String floor, String apartmentNumber, String indications) {
    this.id = id;
    this.country = country;
    this.province = province;
    this.city = city;
    this.street = street;
    this.streetNumber = streetNumber;
    this.floor = floor;
    this.apartmentNumber = apartmentNumber;
    this.indications = indications;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public String getApartmentNumber() {
    return apartmentNumber;
  }

  public void setApartmentNumber(String apartmentNumber) {
    this.apartmentNumber = apartmentNumber;
  }

  public String getIndications() {
    return indications;
  }

  public void setIndications(String indications) {
    this.indications = indications;
  }
}
