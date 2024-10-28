import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/inventario_db";
    private static final String USER = "tu_usuario"; // Reemplaza con tu usuario de MySQL
    private static final String PASSWORD = "tu_contraseña"; // Reemplaza con tu contraseña de MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
