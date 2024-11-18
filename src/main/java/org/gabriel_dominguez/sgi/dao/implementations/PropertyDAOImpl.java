package org.gabriel_dominguez.sgi.dao.implementations;

import org.gabriel_dominguez.sgi.dao.PropertyDAO;
import org.gabriel_dominguez.sgi.models.Owner;
import org.gabriel_dominguez.sgi.models.Property;
import org.gabriel_dominguez.sgi.utils.MariaDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAOImpl implements PropertyDAO {

  private static final String INSERT_PROPERTY = "INSERT INTO properties (type, address, owner, rooms, baths, has_yard, has_balcony) VALUES (?, ?, ?, ?, ?, ?, ?)";
  private static final String SELECT_PROPERTY_BY_ID = "SELECT * FROM properties WHERE id = ?";
  private static final String SELECT_ALL_PROPERTIES = "SELECT * FROM properties";
  private static final String UPDATE_PROPERTY = "UPDATE properties SET type = ?, address = ?, owner = ?, rooms = ?, baths = ?, has_yard = ?, has_balcony = ? WHERE id = ?";
  private static final String DELETE_PROPERTY = "DELETE FROM properties WHERE id = ?";

  @Override
  public Integer save(Property newProperty) throws SQLException {

    try (Connection conn = MariaDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_PROPERTY, Statement.RETURN_GENERATED_KEYS)) {

      // Si hay una dirección nueva, primero insertarla

      stmt.setString(1, newProperty.getType());
      stmt.setInt(2, newProperty.getAddress());
      stmt.setInt(3, newProperty.getOwner());
      stmt.setInt(4, newProperty.getRooms());
      stmt.setObject(5, newProperty.getBaths()); // Address
      stmt.setObject(6, newProperty.getHasYard()); // Address
      stmt.setObject(7, newProperty.getHasBalcony()); // Address

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
  public Property findById(Integer id) {
    return new Property();
  }

  @Override
  public List<Property> findAll() throws SQLException {
    List<Property> propertiesList = new ArrayList<>();

    try (Connection conn = MariaDBConnection.getConnection(); // Conexión a la base de datos
         PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_PROPERTIES); // Preparación de la consulta
         ResultSet rs = stmt.executeQuery()) { // Ejecución de la consulta

      // Iteración a través de los resultados obtenidos
      while (rs.next()) {
        // Obtención de los datos de cada propietario


        int id = rs.getInt("id");
        String type = rs.getString("type");
        Integer address = rs.getInt("address");
        Integer rooms = rs.getInt("rooms");
        Integer baths = rs.getInt("baths");
        Boolean hasYard = rs.getBoolean("has_yard");
        Boolean hasBalcony = rs.getBoolean("has_balcony");
        Integer owner = rs.getInt("owner");

        // Se agrega el propietario a la lista
        propertiesList.add(new Property(type, address, rooms, baths, hasYard, hasBalcony, owner));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Se retorna la lista con todos los propietarios
    return propertiesList;
  }

  @Override
  public Integer modify(Property propertyNewData) throws SQLException {
    try (Connection conn = MariaDBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(UPDATE_PROPERTY, Statement.RETURN_GENERATED_KEYS)) {

      if (propertyNewData.getType() != null) {
        stmt.setString(1, propertyNewData.getType());
      }

      if (propertyNewData.getAddress() != null) {
        stmt.setInt(2, propertyNewData.getAddress());
      }

      if (propertyNewData.getOwner() != null) {
        stmt.setInt(3, propertyNewData.getOwner());
      }

      if (propertyNewData.getRooms() != null) {
        stmt.setInt(4, propertyNewData.getRooms());
      }

      if (propertyNewData.getBaths() != null) {
        stmt.setObject(5, propertyNewData.getBaths());
      }

      if (propertyNewData.getHasYard() != null) {
        stmt.setObject(5, propertyNewData.getHasYard());
      }

      if (propertyNewData.getHasBalcony() != null) {
        stmt.setObject(5, propertyNewData.getHasBalcony());
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
