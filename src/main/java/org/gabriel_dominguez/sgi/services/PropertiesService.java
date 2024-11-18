package org.gabriel_dominguez.sgi.services;

import org.gabriel_dominguez.sgi.dao.implementations.PropertyDAOImpl;
import org.gabriel_dominguez.sgi.models.Property;

import java.sql.SQLException;
import java.util.List;

public class PropertiesService {

  PropertyDAOImpl propertyDAOImpl = new PropertyDAOImpl();

  public void create(Property newProperty) {
    try {
      propertyDAOImpl.save(newProperty);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Property newPropertyData) {
    try {
      propertyDAOImpl.modify(newPropertyData);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(Integer propertyID) {
    propertyDAOImpl.remove(propertyID);
  }

  public List<Property> getAll() {
    try {
      return propertyDAOImpl.findAll();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Property getById(Integer selectedPropertyId) {
    try {
      return propertyDAOImpl.findById(selectedPropertyId);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
