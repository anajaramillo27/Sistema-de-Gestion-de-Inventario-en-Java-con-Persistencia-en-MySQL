public class ProductoNoPerecedero extends Producto {

    public ProductoNoPerecedero(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }

    @Override
    public String getTipo() {
        return "No Perecedero";
    }
}
