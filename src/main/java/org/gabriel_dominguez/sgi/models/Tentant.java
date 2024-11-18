package org.gabriel_dominguez.sgi.models;

import java.util.List;

public class Tentant {
  protected Integer id;
  
  protected String firstName;

  protected String lastName;

  protected String dni;

  protected String phone;

  protected Address address;

  protected List<Property> properties;

  protected List<Contract> contracts;
}
