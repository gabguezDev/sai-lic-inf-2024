package org.gabriel_dominguez.sgi.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.gabriel_dominguez.sgi.dao.implementations.OwnerDAOImpl;
import org.gabriel_dominguez.sgi.models.Owner;

import java.sql.SQLException;
import java.util.List;

public class OwnersServices {

  OwnerDAOImpl ownerDAOImpl = new OwnerDAOImpl();

  public void create(Owner newOwner) {
    try {
      ownerDAOImpl.save(newOwner);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void update(Owner newOwnerData) {
    try {
      ownerDAOImpl.modify(newOwnerData);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void delete(Integer ownerID) {
    ownerDAOImpl.remove(ownerID);
  }

  public List<Owner> getAll() {
    try {
      List<Owner> ownersList = ownerDAOImpl.findAll();
      return ownersList;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Owner getById(Integer selectedOwnerId) {
    try {
      Owner ownersList = ownerDAOImpl.findById(selectedOwnerId);
      return ownersList;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public ObservableList<Owner> getOwnersObservableList() {
    ObservableList<Owner> ownersList = FXCollections.observableArrayList();

    ownersList.addAll(getAll());

    return ownersList;
  }
}
