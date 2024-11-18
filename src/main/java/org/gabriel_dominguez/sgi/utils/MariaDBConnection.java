package org.gabriel_dominguez.sgi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConnection {

  private static final String URL = "jdbc:mariadb://localhost:3306/sgi_db";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "HabEtAnemujoJEH3yaVIlaHEMEG7vE";
  private static Connection connection = null; // Instancia única de la conexión


  // Metodo privado para crear la conexión si no existe
  private static void createConnection() throws SQLException {
    // Verificar si la conexión no existe o está cerrada

    if (connection == null || connection.isClosed()) {
      // Establecer una nueva conexión usando DriverManager de JDBC
      try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
  }

  // Metodo estático para obtener la conexión (singleton)
  public static Connection getConnection() throws SQLException, ClassNotFoundException {
    // Verificar si la conexión no existe o está cerrada
    if (connection == null || connection.isClosed()) {
      createConnection(); // Crear la conexión si no existe
    }
    return connection; // Devolver la instancia de la conexión
  }

  // Metodo estático para cerrar la conexión y recursos relacionados
  public static void cerrarConexion() {
    try {
      // Verificar si la conexión no es nula y no está cerrada
      if (connection != null && !connection.isClosed()) {
        connection.close(); // Cerrar la conexión
        System.out.println("Conexión cerrada correctamente."); // Mensaje de confirmación
      }
    } catch (SQLException e) {
      // Capturar y manejar cualquier excepción SQL al cerrar la conexión
      System.err.println("Error al cerrar la conexión: " + e.getMessage());
      e.printStackTrace(); // Imprimir el rastreo de la excepción
    }
  }
}
