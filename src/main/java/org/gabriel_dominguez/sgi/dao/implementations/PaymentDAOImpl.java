package org.gabriel_dominguez.sgi.dao.implementations;

import org.gabriel_dominguez.sgi.dao.PaymentDAO;
import org.gabriel_dominguez.sgi.models.Payment;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

  private static final String INSERT_QUERY = "INSERT INTO payments (name, email, password) VALUES (?, ?, ?)";
  private static final String SELECT_BY_ID_QUERY = "SELECT * FROM payments WHERE id = ?";
  private static final String SELECT_ALL_QUERY = "SELECT * FROM payments";
  private static final String UPDATE_QUERY = "UPDATE payments SET name = ?, email = ?, password = ? WHERE id = ?";
  private static final String DELETE_QUERY = "DELETE FROM payments WHERE id = ?";


  @Override
  public Integer save(Payment obj) {
    return null;
  }

  @Override
  public Payment findById(Integer id) {
    return new Payment();
  }

  @Override
  public List<Payment> findAll() {
    return List.of();
  }

  @Override
  public Integer modify(Payment obj) {
    return null;
  }

  @Override
  public Integer remove(Integer id) {
    return -1;
  }
}
