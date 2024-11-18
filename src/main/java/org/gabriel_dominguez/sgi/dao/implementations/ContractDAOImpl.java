package org.gabriel_dominguez.sgi.dao.implementations;

import org.gabriel_dominguez.sgi.dao.ContractDAO;
import org.gabriel_dominguez.sgi.models.Contract;

import java.util.List;

public class ContractDAOImpl implements ContractDAO {

  private static final String INSERT_QUERY = "INSERT INTO contracts (name, email, password) VALUES (?, ?, ?)";
  private static final String SELECT_BY_ID_QUERY = "SELECT * FROM contracts WHERE id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM contracts";
  private static final String UPDATE_QUERY = "UPDATE contracts SET name = ?, email = ?, password = ? WHERE id = ?";
  private static final String DELETE_QUERY = "DELETE FROM contracts WHERE id = ?";

  @Override
  public Integer save(Contract obj) {
    return null;
  }

  @Override
  public Contract findById(Integer id) {
    return new Contract();
  }

  @Override
  public List<Contract> findAll() {
    return List.of();
  }

  @Override
  public Integer modify(Contract newContractData) {
    return -1;
  }

  @Override
  public Integer remove(Integer id) {
    return null;
  }
}
