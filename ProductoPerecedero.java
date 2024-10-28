
public class ProductoPerecedero extends Producto {
	    private String fechaVencimiento;

	    public ProductoPerecedero(int id, String nombre, double precio, String fechaVencimiento) {
	        super(id, nombre, precio);
	        this.fechaVencimiento = fechaVencimiento;
	    }

	    public String getFechaVencimiento() {
	        return fechaVencimiento;
	    }

	    @Override
	    public String getTipo() {
	        return "Perecedero";
	    }
	}
