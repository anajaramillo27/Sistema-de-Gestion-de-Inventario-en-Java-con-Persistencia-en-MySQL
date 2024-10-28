import java.sql.*;
import java.util.ArrayList;

public class Inventario {
    
    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (id, nombre, precio, tipo, fecha_vencimiento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, producto.getId());
            pstmt.setString(2, producto.getNombre());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setString(4, producto.getTipo());

            if (producto instanceof ProductoPerecedero) {
                pstmt.setDate(5, Date.valueOf(((ProductoPerecedero) producto).getFechaVencimiento()));
            } else {
                pstmt.setNull(5, Types.DATE);
            }

            pstmt.executeUpdate();
            System.out.println("Producto agregado exitosamente en la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar el producto.");
        }
    }

    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el producto.");
            return false;
        }
    }

    public Producto buscarProducto(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Producto producto = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String tipo = rs.getString("tipo");

                if (tipo.equals("Perecedero")) {
                    String fechaVencimiento = rs.getDate("fecha_vencimiento").toString();
                    producto = new ProductoPerecedero(id, nombre, precio, fechaVencimiento);
                } else {
                    producto = new ProductoNoPerecedero(id, nombre, precio);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al buscar el producto.");
        }
        return producto;
    }

    public void listarProductos() {
        String sql = "SELECT * FROM productos";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Listado de productos en la base de datos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String tipo = rs.getString("tipo");
                String fechaVencimiento = rs.getDate("fecha_vencimiento") != null ? rs.getDate("fecha_vencimiento").toString() : "N/A";

                System.out.println("ID: " + id + " - Nombre: " + nombre + " - Tipo: " + tipo + " - Precio: " + precio + " - Fecha Vencimiento: " + fechaVencimiento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al listar los productos.");
        }
    }
}
