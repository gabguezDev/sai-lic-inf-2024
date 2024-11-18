package org.gabriel_dominguez.sgi.dao.implementations;

import org.gabriel_dominguez.sgi.dao.OwnerDAO;
import org.gabriel_dominguez.sgi.models.Owner;
import org.gabriel_dominguez.sgi.utils.MariaDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {
  // Consultas SQL básicas para Owner
  private static final String INSERT_OWNER = "INSERT INTO owners (first_name, last_name, dni, phone, address) VALUES (?, ?, ?, ?, ?)";
  private static final String SELECT_OWNER_BY_ID = "SELECT * FROM owners WHERE id = ?";
  private static final String SELECT_ALL_OWNERS = "SELECT * FROM owners";
  private static final String UPDATE_OWNER = "UPDATE owners SET first_name = ?, last_name = ?, dni = ?, phone = ? WHERE id = ?";
  private static final String DELETE_OWNER = "DELETE FROM owners WHERE id = ?";

  // Consultas para relaciones
  private static final String SELECT_PROPERTIES_BY_OWNER = "SELECT * FROM properties WHERE owner_id = ?";
  private static final String SELECT_CONTRACTS_BY_OWNER = "SELECT * FROM contracts WHERE owner_id = ?";
  private static final String SEARCH_OWNERS = "SELECT o.*, a.* FROM owners o LEFT JOIN addresses a ON o.address_id = a.id " + "WHERE o.dni LIKE ? OR o.first_name LIKE ? OR o.last_name LIKE ?";

  @Override
  public Integer save(Owner newOwner) throws SQLException {

    try (Connection conn = MariaDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_OWNER, Statement.RETURN_GENERATED_KEYS)) {

      // Si hay una dirección nueva, primero insertarla

      stmt.setString(1, newOwner.getFirstName());
      stmt.setString(2, newOwner.getLastName());
      stmt.setString(3, newOwner.getDni());
      stmt.setString(4, newOwner.getPhone());
      stmt.setObject(5, newOwner.getAddress()); // Address

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
  public Owner findById(Integer id) throws SQLException {
    try (Connection conn = MariaDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_OWNER_BY_ID)) {

      stmt.setInt(1, id);  // Primero establecemos el parámetro

      try (ResultSet rs = stmt.executeQuery()) {  // Luego ejecutamos la consulta
        if (rs.next()) {
          return new Owner(rs.getInt("id"), rs.getString("first_name"),  // Corregido el nombre de la columna
              rs.getString("last_name"),   // Corregido el nombre de la columna
              rs.getString("dni"), rs.getString("phone"), rs.getInt("address"));
        }
      }
    } catch (Exception e) {
      throw new SQLException("Error al buscar el propietario: " + e.getMessage(), e);
    }
    return null;
  }

  @Override
  public List<Owner> findAll() throws SQLException {
    List<Owner> ownersList = new ArrayList<>();

    try (Connection conn = MariaDBConnection.getConnection(); // Conexión a la base de datos
         PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_OWNERS); // Preparación de la consulta
         ResultSet rs = stmt.executeQuery()) { // Ejecución de la consulta

      // Iteración a través de los resultados obtenidos
      while (rs.next()) {
        // Obtención de los datos de cada propietario
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String dni = rs.getString("dni");
        String phone = rs.getString("phone");
        Integer address = rs.getInt("address");

        // Se agrega el propietario a la lista
        ownersList.add(new Owner(id, firstName, lastName, dni, phone, address));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Se retorna la lista con todos los propietarios
    return ownersList;
  }


  @Override
  public Integer modify(Owner ownerNewData) throws SQLException {
    try (Connection conn = MariaDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_OWNER, Statement.RETURN_GENERATED_KEYS)) {

      if (ownerNewData.getFirstName() != null) {
        stmt.setString(1, ownerNewData.getFirstName());
      }

      if (ownerNewData.getLastName() != null) {
        stmt.setString(2, ownerNewData.getLastName());
      }

      if (ownerNewData.getDni() != null) {
        stmt.setString(3, ownerNewData.getDni());
      }

      if (ownerNewData.getPhone() != null) {
        stmt.setString(4, ownerNewData.getPhone());
      }

      if (ownerNewData.getId() != null) {
        stmt.setObject(5, ownerNewData.getId());
      }

      stmt.executeUpdate();

      return 1;

    } catch (ClassNotFoundException | SQLException e) {
      throw new SQLException("Error de conexión a la base de datos", e);
    }
  }

  @Override
  public Integer remove(Integer id) {
    try (Connection conn = MariaDBConnection.getConnection(); // Conexión a la base de datos
         PreparedStatement stmt = conn.prepareStatement(DELETE_OWNER, Statement.RETURN_GENERATED_KEYS); // Preparación de la consulta
    ) {

      stmt.setInt(1, id);

      stmt.executeUpdate();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return generatedKeys.getInt(1);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return -1;
  }
}