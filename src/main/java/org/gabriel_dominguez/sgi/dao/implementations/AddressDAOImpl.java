package org.gabriel_dominguez.sgi.dao.implementations;

import org.gabriel_dominguez.sgi.dao.AddressDAO;
import org.gabriel_dominguez.sgi.models.Address;
import org.gabriel_dominguez.sgi.utils.MariaDBConnection;

import java.sql.*;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
  // Consultas SQL básicas para Owner

  private static final String INSERT_ADDRESS =
      "INSERT INTO addresses (country, province, city, street, street_number, floor, apartment_number, indications) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  private static final String SELECT_ADDRESS_BY_ID =
      "SELECT * FROM addresses WHERE id = ?";
  private static final String SELECT_ALL_ADDRESSES =
      "SELECT * FROM addresses";
  private static final String UPDATE_ADDRESS =
      "UPDATE addresses SET country = ?, province = ?, city = ?, street = ?, street_number = ?, floor = ?, apartment_number = ?, indications = ? WHERE id = ?";
  private static final String DELETE_ADDRESS =
      "DELETE FROM addresses WHERE id = ?";


  @Override
  public Integer save(Address newAddress) throws SQLException {
    try (Connection conn = MariaDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, newAddress.getCountry());
      stmt.setString(2, newAddress.getProvince());
      stmt.setString(3, newAddress.getCity());
      stmt.setString(4, newAddress.getStreet());
      stmt.setString(5, newAddress.getStreetNumber());
      stmt.setString(6, newAddress.getFloor());
      stmt.setString(7, newAddress.getApartmentNumber());
      stmt.setString(8, newAddress.getIndications());

      stmt.executeUpdate();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return generatedKeys.getInt(1);
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      throw new SQLException("Error de conexión a la base de datos", e);
    }

    return -1;
  }

  @Override
  public Address findById(Integer id) throws SQLException {
    try (Connection conn = MariaDBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(SELECT_ADDRESS_BY_ID)) {

      stmt.setInt(1, id);  // Cambiado a setInt ya que id es Integer

      try (ResultSet rs = stmt.executeQuery()) {  // Cambiado a executeQuery() ya que es un SELECT
        if (rs.next()) {
          return new Address(
              rs.getInt("id"),
              rs.getString("country"),
              rs.getString("province"),
              rs.getString("city"),
              rs.getString("street"),
              rs.getString("street_number"),
              rs.getString("floor"),
              rs.getString("apartment_number"),
              rs.getString("indications")
          );
        }
      }

    } catch (ClassNotFoundException | SQLException e) {
      throw new SQLException("Error de conexión a la base de datos", e);
    }

    return null;  // Retorna null si no se encuentra la dirección
  }

  @Override
  public List<Address> findAll() throws SQLException {
    return List.of();
  }

  @Override
  public Integer modify(Address newAddressData) throws SQLException {
    try (Connection conn = MariaDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_ADDRESS, Statement.RETURN_GENERATED_KEYS)) {

      if (newAddressData.getCountry() != null) {
        stmt.setString(1, newAddressData.getCountry());
      }

      if (newAddressData.getProvince() != null) {
        stmt.setString(2, newAddressData.getProvince());
      }

      if (newAddressData.getCity() != null) {
        stmt.setString(3, newAddressData.getCity());
      }

      if (newAddressData.getStreet() != null) {
        stmt.setString(4, newAddressData.getStreet());
      }

      if (newAddressData.getStreetNumber() != null) {
        stmt.setObject(5, newAddressData.getStreetNumber());
      }

      if (newAddressData.getFloor() != null) {
        stmt.setObject(6, newAddressData.getFloor());
      }

      if (newAddressData.getApartmentNumber() != null) {
        stmt.setObject(7, newAddressData.getApartmentNumber());
      }

      if (newAddressData.getIndications() != null) {
        stmt.setObject(8, newAddressData.getIndications());
      }

      if (newAddressData.getId() != null) {
        stmt.setObject(9, newAddressData.getId());
      }

      stmt.executeUpdate();

      return 1;

    } catch (ClassNotFoundException | SQLException e) {
      throw new SQLException("Error de conexión a la base de datos", e);
    }
  }

  @Override
  public Integer remove(Integer id) {
    return -1;
  }
}
