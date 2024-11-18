package org.gabriel_dominguez.sgi.models;

public class Property {
  protected Integer id;

  protected String type;

  protected Integer address;

  protected Integer rooms;

  protected Integer baths;

  protected Boolean hasYard;

  protected Boolean hasBalcony;

  private String fullAddress;


  public Boolean getHasBalcony() {
    return hasBalcony;
  }

  public void setHasBalcony(Boolean hasBalcony) {
    this.hasBalcony = hasBalcony;
  }

  protected Integer owner;

  public Property() {
  }

  public Property(String type, Integer address, Integer rooms, Integer baths, Boolean hasYard, Boolean hasBalcony, Integer owner) {
    this.type = type;
    this.address = address;
    this.rooms = rooms;
    this.baths = baths;
    this.hasYard = hasYard;
    this.hasBalcony = hasBalcony;
    this.owner = owner;
  }


  public Property(Integer id, String type, Integer address, Integer rooms, Integer baths, Boolean hasYard, Boolean hasBalcony, Integer owner) {
    this.id = id;
    this.type = type;
    this.address = address;
    this.rooms = rooms;
    this.baths = baths;
    this.hasYard = hasYard;
    this.hasBalcony = hasBalcony;
    this.owner = owner;
  }

  public Property(String type, Integer rooms, Integer baths, Boolean hasYard, Boolean hasBalcony) {
    this.type = type;
    this.rooms = rooms;
    this.baths = baths;
    this.hasYard = hasYard;
    this.hasBalcony = hasBalcony;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getAddress() {
    return address;
  }

  public void setAddress(Integer address) {
    this.address = address;
  }

  public Integer getRooms() {
    return rooms;
  }

  public void setRooms(Integer rooms) {
    this.rooms = rooms;
  }

  public Integer getBaths() {
    return baths;
  }

  public void setBaths(Integer baths) {
    this.baths = baths;
  }

  public Boolean getHasYard() {
    return hasYard;
  }

  public void setHasYard(Boolean hasYard) {
    this.hasYard = hasYard;
  }

  public Integer getOwner() {
    return owner;
  }

  public void setOwner(Integer owner) {
    this.owner = owner;
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
