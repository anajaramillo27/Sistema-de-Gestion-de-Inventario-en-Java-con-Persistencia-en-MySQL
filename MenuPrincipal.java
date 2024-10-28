import java.util.Scanner;

		public class Main {
		    public static void main(String[] args) {
		        MenuPrincipal menu = new MenuPrincipal();
		        menu.mostrarMenu();
		    }
		}
		    private Inventario inventario = new Inventario();
		    private Scanner scanner = new Scanner(System.in);

		    public void mostrarMenu() {
		        int opcion;
		        do {
		            System.out.println("Gestión de Inventario:");
		            System.out.println("1. Agregar Producto");
		            System.out.println("2. Eliminar Producto");
		            System.out.println("3. Buscar Producto");
		            System.out.println("4. Listar Productos");
		            System.out.println("5. Salir");
		            System.out.print("Seleccione una opción: ");
		            opcion = scanner.nextInt();

		            switch (opcion) {
		                case 1:
		                    agregarProducto();
		                    break;
		                case 2:
		                    eliminarProducto();
		                    break;
		                case 3:
		                    buscarProducto();
		                    break;
		                case 4:
		                    inventario.listarProductos();
		                    break;
		                case 5:
		                    System.out.println("Saliendo del sistema...");
		                    break;
		                default:
		                    System.out.println("Opción no válida.");
		            }
		        } while (opcion != 5);
		    }

		    private void agregarProducto() {
		        System.out.print("Ingrese ID del producto: ");
		        int id = scanner.nextInt();
		        scanner.nextLine(); // limpiar buffer
		        System.out.print("Ingrese nombre del producto: ");
		        String nombre = scanner.nextLine();
		        System.out.print("Ingrese precio del producto: ");
		        double precio = scanner.nextDouble();
		        scanner.nextLine(); // limpiar buffer
		        System.out.print("¿El producto es perecedero? (S/N): ");
		        char esPerecedero = scanner.nextLine().charAt(0);

		        if (esPerecedero == 'S' || esPerecedero == 's') {
		            System.out.print("Ingrese fecha de vencimiento (DD-MM-AAAA): ");
		            String fechaVencimiento = scanner.nextLine();
		            inventario.agregarProducto(new ProductoPerecedero(id, nombre, precio, fechaVencimiento));
		        } else {
		            inventario.agregarProducto(new ProductoNoPerecedero(id, nombre, precio));
		        }
		        System.out.println("Producto agregado exitosamente.");
		    }

		    private void eliminarProducto() {
		        System.out.print("Ingrese ID del producto a eliminar: ");
		        int id = scanner.nextInt();
		        boolean eliminado = inventario.eliminarProducto(id);
		        if (eliminado) {
		            System.out.println("Producto eliminado exitosamente.");
		        } else {
		            System.out.println("Producto no encontrado.");
		        }
		    }

		    private void buscarProducto() {
		        System.out.print("Ingrese ID del producto a buscar: ");
		        int id = scanner.nextInt();
		        Producto producto = inventario.buscarProducto(id);
		        if (producto != null) {
		            System.out.println("Producto encontrado: " + producto.getNombre() + " - Tipo: " + producto.getTipo() + " - Precio: " + producto.getPrecio());
		        } else {
		            System.out.println("Producto no encontrado.");
		        }
		    }
		}
	}

