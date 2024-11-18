package org.gabriel_dominguez.sgi.services;

import org.gabriel_dominguez.sgi.dao.implementations.AddressDAOImpl;
import org.gabriel_dominguez.sgi.models.Address;
import org.gabriel_dominguez.sgi.models.Owner;

import java.sql.SQLException;

public class AddressesServices {

  AddressDAOImpl addressDAOImpl = new AddressDAOImpl();

  public Integer create(Address newAddress) {
    try {
      return addressDAOImpl.save(newAddress);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Address address) {
    try {
      addressDAOImpl.modify(address);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Address getById(Integer address) {
    try {
      return addressDAOImpl.findById(address);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
}
